/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.aiforest.com
 * 注意：
 * 本软件为www.aiforest.com开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.aiforest.cloud.estate.admin.api.es;

import com.aiforest.cloud.common.grpc.api.SalesmanGetResponse;
import com.aiforest.cloud.estate.admin.service.SalesmanService;
import com.aiforest.cloud.estate.common.vo.SalesmanVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aiforest.cloud.common.core.util.R;
import com.aiforest.cloud.estate.admin.service.SmsRecordsService;
import com.aiforest.cloud.estate.admin.service.UserInfoService;
import com.aiforest.cloud.estate.common.dto.SummationDTO;
import com.aiforest.cloud.estate.common.entity.SmsRecords;
import com.aiforest.cloud.estate.common.entity.UserInfo;
import com.aiforest.cloud.estate.common.entity.Week;
import com.aiforest.cloud.weixin.common.util.ThirdSessionHolder;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;



/**
 * 商城用户
 *
 * @author JL
 * @date 2019-12-04 11:09:55
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/es/userinfo")
@Api(value = "userinfo", tags = "房产用户API")
public class UserInfoApi {

    private final UserInfoService userInfoService;
	private final SmsRecordsService smsRecordsService;
	private final SalesmanService salesmanService;

	/**
     * 查询商城用户
     * @return R
     */
    @GetMapping
    public R getById() {
		UserInfo userInfo = new UserInfo();
//		R checkThirdSession = BaseApi.checkThirdSession(userInfo, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		userInfo.setId(ThirdSessionHolder.getEstateUserId());
		userInfo = userInfoService.getById(userInfo.getId());
        return R.ok(userInfo);
    }

	@GetMapping("/page")
	public R getPage(Page page, UserInfo userInfo) {
//		R checkThirdSession = BaseApi.checkThirdSession(userInfo, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
//		userInfo.setId(ThirdSessionHolder.getEstateUserId());
		return R.ok(userInfoService.page(page, Wrappers.query(userInfo)));
	}

	@PutMapping
	public R updateById(HttpServletRequest request, @RequestBody UserInfo userInfo) {
//		R checkThirdSession = BaseApi.checkThirdSession(userInfo, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}
		userInfo.setId(ThirdSessionHolder.getEstateUserId());
		return R.ok(userInfoService.updateById(userInfo));
	}

	@GetMapping("/listvalid")
	public R getListValid () {
		UserInfo userInfo = new UserInfo();
		userInfo.setInvitee(ThirdSessionHolder.getEstateUserId());
		return R.ok(userInfoService.list(Wrappers.query(userInfo).lambda().isNotNull(UserInfo::getPhone)));
	}

	@PostMapping("/phone")
	public UserInfo mini_getPhone(@RequestBody Map<String,String> param) {

		String session_key = param.get("session_key");
		String encryptedData = param.get("encryptedData");
		String iv = param.get("iv");

		JSONObject obj=getPhoneNumber(session_key,encryptedData,iv);//解密电话号码
        // wxjava解密
		String wphone = WxMaCryptUtils.decrypt(session_key,encryptedData,iv);
		//System.out.println(obj);
		String sphone=obj.get("phoneNumber").toString();

		UserInfo userInfo = new UserInfo();
		userInfo.setId(ThirdSessionHolder.getEstateUserId());
		userInfo = userInfoService.getById(userInfo.getId());


		if (userInfo != null) {
			userInfo.setPhone(sphone);
			userInfoService.updateById(userInfo);
		}

		return userInfo;

	}

	//解析电话号码
	private JSONObject getPhoneNumber(String session_key, String encryptedData, String iv) {
		byte[] dataByte = Base64.decode(encryptedData);

		byte[] keyByte = Base64.decode(session_key);

		byte[] ivByte = Base64.decode(iv);
		try {

			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.parseObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

//	@GetMapping("/invitee")
//	public R getInvitee() {
//		UserInfo userInfo = userInfoService.getById(ThirdSessionHolder.getEstateUserId());
//		if (userInfo != null) {
//			if (userInfo.getInvitee() == null || userInfo.getInvitee().equals("")) {
//				UserInfo inviteeUser = userInfoService.getRandSalesman();
//				if (inviteeUser == null) {
//					log.info("未能查找到有效用户:{}");
//					return R.failed("未能查找到有效用户");
//				}
//				userInfo.setInvitee(inviteeUser.getId());
//			} else {
//				UserInfo inviteeUser = userInfoService.getById(userInfo.getInvitee());
//				if (inviteeUser == null) {
//					inviteeUser = userInfoService.getRandSalesman();
//					if (inviteeUser == null) {
//						log.info("未能查找到有效用户:{}");
//						return R.failed("未能查找到有效用户");
//					}
//					userInfo.setInvitee(inviteeUser.getId());
//				} else {
//					if (!inviteeUser.getUserType().equals("1")) {
//						inviteeUser = userInfoService.getRandSalesman();
//						if (inviteeUser == null) {
//							log.info("未能查找到有效用户:{}");
//							return R.failed("未能查找到有效用户");
//						}
//						userInfo.setInvitee(inviteeUser.getId());
//					}
//				}
//			}
//			userInfoService.updateById(userInfo);
//		} else {
//			log.info("未能查到用户:{}");
//			return R.failed("未能查找用户");
//		}
//
//		return R.ok(userInfo);
//	}

	@GetMapping("/distribution")
	public R getDistribution(@RequestParam("affId") String affiliationId) {
    	if (affiliationId == null || affiliationId.equals("")) {
			return R.failed("未能查找到有效楼盘");
		}
		UserInfo userInfo = userInfoService.getById(ThirdSessionHolder.getEstateUserId());
		if (userInfo != null) {
			List<SalesmanVO> salesmanVOS = salesmanService.getAll(affiliationId);

			if (salesmanVOS.size() <= 0) {
				return R.failed("未能查找到有效用户");
			}

			int ra = new Random().nextInt(salesmanVOS.size());

			if (userInfo.getDistribution() == null || userInfo.getDistribution().length <= 0) {

				userInfo.setDistribution(salesmanVOS.get(ra).getId().split(","));

			} else {

				List<String> list = Arrays.asList(userInfo.getDistribution());

				List<SalesmanVO> salesmanVOList = salesmanVOS.stream().filter(
						salesman ->
								list.contains(salesman.getId())).collect(Collectors.toList());

				if (salesmanVOList.size() <= 0) {

//					int ra = new Random().nextInt(salesmanVOS.size());

					userInfo.setDistribution(insert(userInfo.getDistribution(), salesmanVOS.get(ra).getId()));
				}

			}
			userInfoService.updateById(userInfo);
		} else {
			log.info("未能查到用户:{}");
			return R.failed("未能查找用户");
		}

		return R.ok(userInfo);
	}

	@GetMapping("/invitee/{id}")
	public R updateByInvitee(@PathVariable("id") String id) {
//		R checkThirdSession = BaseApi.checkThirdSession(userInfo, request);
//		if(!checkThirdSession.isOk()) {//检验失败，直接返回失败信息
//			return checkThirdSession;
//		}

		//TODO broker registration information is incomplete

		UserInfo userInfo = userInfoService.getById(ThirdSessionHolder.getEstateUserId());
		if (userInfo == null) {
			log.info("未能查到用户:{}");
			return R.failed("未能查找用户");
		}
		userInfo.setInvitee(id);
		UserInfo inviteeUser = userInfoService.getById(id);
		if (inviteeUser != null) {
			if (inviteeUser.getDistribution() != null && inviteeUser.getDistribution().length > 0) {
				userInfo.setDistribution(inviteeUser.getDistribution());
			}
			return R.ok(userInfoService.updateById(userInfo));
		}
		return R.ok(userInfoService.updateById(userInfo));
	}

	@GetMapping("/distribution/{id}")
	public R updateByDistribution(@PathVariable("id") String id) {

		//TODO broker registration information is incomplete
		UserInfo userInfo = userInfoService.getById(ThirdSessionHolder.getEstateUserId());
		if (userInfo == null) {
			log.info("未能查到用户:{}");
			return R.failed("未能查找用户");
		}
		SalesmanGetResponse response = salesmanService.get(id);
		if (response.getSalesman() == null) {
			log.info("未能查到用户:{}");
			return R.failed("未能查找用户");
		}
		if (userInfo.getDistribution() != null && userInfo.getDistribution().length > 0) {
			List<String> list = Arrays.asList(userInfo.getDistribution());
			if (list.contains(response.getSalesman().getId()) && response.getSalesman().getUserStatus().equals("0")) {
				return R.ok("已绑定用户");
			}
			// Do not delete processing
			userInfo.setDistribution(insert(userInfo.getDistribution(), response.getSalesman().getId()));
		}

//		List list = new ArrayList<>();
//		list.add(response.getSalesman().getId());
//		String[] array = new String[list.size()+1];
		userInfo.setDistribution(response.getSalesman().getId().split(","));

		return R.ok(userInfoService.updateById(userInfo));
	}

//	private static String[] insert(String[] arr, String str) {
//		int size = arr.length;  //获取数组长度
//		String[] tmp = new String[size + 1];  //新建临时字符串数组，在原来基础上长度加一
//		for (int i = 0; i < size; i++){  //先遍历将原来的字符串数组数据添加到临时字符串数组
//			tmp[i] = arr[i];
//		}
//		tmp[size] = str;  //在最后添加上需要追加的数据
//		return tmp;  //返回拼接完成的字符串数组
//	}

	private static String[] insert(String[] arr, String str)
	{
		int size = arr.length;
		String[] tmp = new String[size + 1];
		System.arraycopy(arr, 0, tmp, 0, size);
		tmp[size] = str;
		return tmp;
	}


	@GetMapping("/summation")
	public R getSummation() {
		SummationDTO summationDTO = new SummationDTO();
		summationDTO.setShareUserCount(userInfoService.count(Wrappers.<UserInfo>lambdaQuery()
				.eq(UserInfo :: getInvitee, ThirdSessionHolder.getEstateUserId())));
		summationDTO.setRecommendCount(smsRecordsService.count(Wrappers.<SmsRecords>lambdaQuery()
				.eq(SmsRecords :: getReceiverId, ThirdSessionHolder.getEstateUserId())));
		return R.ok(summationDTO);
	}

	@PostMapping("/statement/{id}")
	public R statement(@PathVariable("id") String id, @RequestParam("duration") String duration, @RequestParam("portion") String portion) {

//		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
//		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
//		if (1 == dayWeek) {
//			cal.add(Calendar.DAY_OF_MONTH, -1);
//		}
//		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
//		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
//		cal.setFirstDayOfWeek(Calendar.MONDAY);
//		// 获得当前日期是一个星期的第几天
//		int day = cal.get(Calendar.DAY_OF_WEEK);
//		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
//		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
//		String imptimeBegin = sdf.format(cal.getTime());
//		// System.out.println("所在周星期一的日期：" + imptimeBegin);
//		cal.add(Calendar.DATE, 6);
//		String imptimeEnd = sdf.format(cal.getTime());
//		// System.out.println("所在周星期日的日期：" + imptimeEnd);
//		System.out.println(imptimeBegin + "," + imptimeEnd);

        if (duration.equals("1")) {

			LocalDate ldt = LocalDate.now();
			List<LocalDate> dates = new ArrayList<>();
			List<SummationDTO> summations = new ArrayList<>();

			dates.add(ldt.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));
			dates.add(ldt.with(TemporalAdjusters.previousOrSame(DayOfWeek.TUESDAY)));
			dates.add(ldt.with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY)));
			dates.add(ldt.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY)));
			dates.add(ldt.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY)));
			dates.add(ldt.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY)));
			dates.add(ldt.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)));

			int week = ldt.get(WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek());

			int l = dates.size();
			for (int i = 0; i < l; i++) {
//				System.out.println(dates.get(i));
				SummationDTO summationDTO = new SummationDTO();
				if (i < week) {
					LocalDateTime today_start = LocalDateTime.of(dates.get(i), LocalTime.MIN);
					LocalDateTime today_end = LocalDateTime.of(dates.get(i), LocalTime.MAX);

					summationDTO.setShareUserCount(userInfoService.count(Wrappers.<UserInfo>lambdaQuery()
							.eq(UserInfo :: getInvitee, id)
							.gt(UserInfo :: getCreateTime, today_start)
							.lt(UserInfo :: getCreateTime, today_end)));
//							.and(wrapper -> wrapper
//									.gt(UserInfo::getTagidList,"["+id+"]")
//									.and
//									.lt(UserInfo::getTagidList,","+id+"]"))));

					summationDTO.setRecommendCount(smsRecordsService.count(Wrappers.<SmsRecords>lambdaQuery()
							.eq(SmsRecords :: getReceiverId, id)
							.gt(SmsRecords :: getCreateTime, today_start)
							.lt(SmsRecords :: getCreateTime, today_end)));

				}
				summations.add(summationDTO);

			}

			return R.ok(summations);

		} else if (duration.equals("2")) {

			LocalDate firstDay = LocalDate.now().withMonth(Integer.valueOf(portion)).with(TemporalAdjusters.firstDayOfMonth());
			LocalDate lastDay = LocalDate.now().withMonth(Integer.valueOf(portion)).with(TemporalAdjusters.lastDayOfMonth());


			List<Week> listWeek = getWeekList(firstDay, lastDay);

			List<SummationDTO> summations = new ArrayList<>();

			int l = listWeek.size();

			for (int i = 0; i < l; i++) {

				SummationDTO summationDTO = new SummationDTO();

					summationDTO.setShareUserCount(userInfoService.count(Wrappers.<UserInfo>lambdaQuery()
							.eq(UserInfo :: getInvitee, id)
							.gt(UserInfo :: getCreateTime, listWeek.get(i).getStartTime())
							.lt(UserInfo :: getCreateTime, listWeek.get(i).getEndTime())));
//							.and(wrapper -> wrapper
//									.gt(UserInfo::getTagidList,"["+id+"]")
//									.and
//									.lt(UserInfo::getTagidList,","+id+"]"))));

					summationDTO.setRecommendCount(smsRecordsService.count(Wrappers.<SmsRecords>lambdaQuery()
							.eq(SmsRecords :: getReceiverId, id)
							.gt(SmsRecords :: getCreateTime, listWeek.get(i).getStartTime())
							.lt(SmsRecords :: getCreateTime, listWeek.get(i).getEndTime())));

				summations.add(summationDTO);

			}


			return R.ok(summations);

		}


//		Integer week = ldt.get(WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek());

//		Date first = firstMonthDate(new Date());
//		Map<Integer, WeekRangeDTO> maps = new HashMap<Integer, WeekRangeDTO>();
//		getWeekBeginAndEnd(1,first,maps);
//
//		Set<Integer> set = maps.keySet();
//		for(Integer key : set){
//			WeekRangeDTO range = maps.get(key);
//			System.out.println(String.format("第%d周,开始日期：%s,结束日期：%s", key, sdf.format(range.getBegin()), sdf.format(range.getEnd())));
//		}



		return R.ok();
	}

	// 月初
//	public static Date firstMonthDate(Date date){
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
//		return calendar.getTime();
//	}
//
//	// 月末
//	public static Date lastMonthDate(Date date){
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//		return calendar.getTime();
//	}
//
//	// 星期几
//	public static int week(Date date){
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
//	}
//
//	// 下一天
//	public static Date nextDate(Date date){
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.DAY_OF_MONTH, 1);
//		return calendar.getTime();
//	}
//
//	// 每周开始结束时间
//	public static void getWeekBeginAndEnd(int index,Date currentDate,Map<Integer,WeekRangeDTO> maps){
//		//月末
//		Date lastMonthDate = lastMonthDate(currentDate);
//		int week = week(currentDate);
//		if(null == maps){
//			WeekRangeDTO range = new WeekRangeDTO(currentDate, currentDate);
//			maps = new HashMap<Integer, WeekRangeDTO>();
//			maps.put(index,range);
//		}else{
//			WeekRangeDTO range = maps.get(index);
//			if(null == range){
//				range = new WeekRangeDTO(currentDate);
//			}
//			range.setEnd(currentDate);
//			maps.put(index,range);
//		}
//
//		if(currentDate.equals(lastMonthDate)){
//			return;
//		}
//
//		if(week == 0){
//			index++;
//		}
//
//		getWeekBeginAndEnd(index,nextDate(currentDate),maps);
//	}

	public static List<Week> getWeekList(LocalDate startTime, LocalDate endTime) {
		List<Week> resultList = new ArrayList<Week>();
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		//判断是否同一周
		WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,4);
		if(startTime.get(weekFields.weekOfWeekBasedYear()) == endTime.get(weekFields.weekOfWeekBasedYear())){
			Week firstWeek = new Week();
			firstWeek.setDay(startTime + "~" + endTime);
			firstWeek.setStartTime(LocalDateTime.of(startTime, LocalTime.MIN));
			firstWeek.setEndTime(LocalDateTime.of(endTime, LocalTime.MAX));
//			firstWeek.setStartTimeNumber(Integer.valueOf(df.format(startTime)));
//			firstWeek.setEndTimeNumber(Integer.valueOf(df.format(endTime)));
			resultList.add(firstWeek);
			return resultList;
		}

		//开始周
		TemporalAdjuster FIRST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue()));
		LocalDateTime startFirstWeek = LocalDateTime.of(startTime.with(FIRST_OF_WEEK), LocalTime.MIN); //开始周开始日期
		TemporalAdjuster LAST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
		LocalDateTime endFirstWeek = LocalDateTime.of(startTime.with(LAST_OF_WEEK), LocalTime.MAX);     //开始周结束日期


		//结束周
		TemporalAdjuster FIRST_OF_WEEK1 = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue()));
		LocalDateTime startLastWeek = LocalDateTime.of(endTime.with(FIRST_OF_WEEK1), LocalTime.MIN);
		TemporalAdjuster LAST_OF_WEEK1 = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
		LocalDateTime endLastWeek = LocalDateTime.of(endTime.with(LAST_OF_WEEK1), LocalTime.MAX);

		//将第一周添加
		Week firstWeek = new Week();
		firstWeek.setDay(startTime + "~" + endFirstWeek);
		firstWeek.setStartTime(LocalDateTime.of(startTime, LocalTime.MIN));
		firstWeek.setEndTime(endFirstWeek);
//		firstWeek.setStartTimeNumber(Integer.valueOf(df.format(startTime)));
//		firstWeek.setEndTimeNumber(Integer.valueOf(df.format(endFirstWeek)));
		resultList.add(firstWeek);

		while (true) {
			startFirstWeek = startFirstWeek.plusDays(7);
			if (startFirstWeek.with(LAST_OF_WEEK).equals(startLastWeek.with(LAST_OF_WEEK1))) {
				break;
			} else {
				Week week = new Week();
				week.setDay(startFirstWeek.with(FIRST_OF_WEEK) + "~" + startFirstWeek.with(LAST_OF_WEEK));
				week.setStartTime(startFirstWeek.with(FIRST_OF_WEEK));
				week.setEndTime(startFirstWeek.with(LAST_OF_WEEK));
//				week.setStartTimeNumber(Integer.valueOf(df.format(startFirstWeek.with(FIRST_OF_WEEK))));
//				week.setEndTimeNumber(Integer.valueOf(df.format(startFirstWeek.with(LAST_OF_WEEK))));
				resultList.add(week);
				//System.out.println("日期="+startFirstWeek+"开始周="+startFirstWeek.with(FIRST_OF_WEEK)+"结束周="+startFirstWeek.with(LAST_OF_WEEK));
			}
		}
		Week lastWeek = new Week();
		lastWeek.setDay(startLastWeek + "~" + endTime);
		lastWeek.setStartTime(startLastWeek);
		lastWeek.setEndTime(LocalDateTime.of(endTime, LocalTime.MAX));
//		lastWeek.setStartTimeNumber(Integer.valueOf(df.format(startLastWeek)));
//		lastWeek.setEndTimeNumber(Integer.valueOf(df.format(endTime)));
		resultList.add(lastWeek);
		return resultList;
	}

}
