package com.aiforest.cloud.broker.admin.delegates;

import com.aiforest.cloud.broker.admin.service.ReferralsService;
import com.aiforest.cloud.broker.admin.service.UserInfoService;
import com.aiforest.cloud.broker.admin.service.VariableDefinitionService;
import com.aiforest.cloud.broker.common.dto.UserInfoDto;
import com.aiforest.cloud.broker.common.entity.Referrals;
import com.aiforest.cloud.broker.common.entity.UserInfo;
import com.aiforest.cloud.broker.common.entity.VariableDefinition;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class PigeonholeDelegate implements JavaDelegate {

	private final ReferralsService referralsService;
	private final VariableDefinitionService variableDefinitionService;

	private final UserInfoService userInfoService;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {


		String phone = (String)delegateExecution.getVariable("phone");
		String affiliationId = (String)delegateExecution.getVariable("affiliationId");
		String tenantId = (String)delegateExecution.getVariable("tenantId");
		String brokerId = (String)delegateExecution.getVariable("brokerId");
		String customerName = (String)delegateExecution.getVariable("customerName");
		String gender = (String)delegateExecution.getVariable("gender");
		String description = (String)delegateExecution.getVariable("description");
		String brokerName = (String)delegateExecution.getVariable("brokerName");
		String salesmanId = (String)delegateExecution.getVariable("salesmanId");
		String inviteeId = (String)delegateExecution.getVariable("inviteeId");
		String brokerPhone = (String)delegateExecution.getVariable("brokerPhone");

		Referrals referrals = new Referrals();
		referrals.setAffiliationId(affiliationId);
		referrals.setTenantId(tenantId);

		referrals.setPhone(phone);
		referrals.setCustomerName(customerName);
		referrals.setGender(gender);
		referrals.setDescription(description);

		referrals.setBrokerId(brokerId);
		referrals.setBrokerName(brokerName);
		referrals.setBrokerPhone(brokerPhone);

		referrals.setSalesmanId(salesmanId);
		referrals.setInviteeId(inviteeId);

		// system task add current agent not need
//		referrals.setCurrentHandler(delegateExecution.getCurrentActivityName());

		referrals.setInstanceId(delegateExecution.getProcessInstanceId());

		referralsService.save(referrals);

		List<VariableDefinition> variableDefinitionList =
				variableDefinitionService.list(Wrappers.<VariableDefinition>lambdaQuery()
						.eq(VariableDefinition::getDefinitionId, delegateExecution.getProcessDefinitionId())
				.in(VariableDefinition::getVariableId, "directPush", "manuallyCheckGuest", "distinguishRoles"
						, "inviteDuration", "depositDuration", "executionDuration"));

		if (variableDefinitionList.size() != 6) return;

		List<VariableDefinition> userRolesDefinition = findObjFromList(variableDefinitionList, "variableId", "distinguishRoles");
		List<VariableDefinition> directPushDefinition = findObjFromList(variableDefinitionList, "variableId", "directPush");
		List<VariableDefinition> mcgDefinition = findObjFromList(variableDefinitionList, "variableId", "manuallyCheckGuest");
		List<VariableDefinition> inviteDDefinition = findObjFromList(variableDefinitionList, "variableId", "inviteDuration");
		List<VariableDefinition> mcgDdepositDDefinition = findObjFromList(variableDefinitionList, "variableId", "depositDuration");
		List<VariableDefinition> executionDDefinition = findObjFromList(variableDefinitionList, "variableId", "executionDuration");


//		Object rolesName = userRolesDefinition.get(0).getVariableType().equals("string") ? userRolesDefinition.get(0).getVariableString() :
//				(userRolesDefinition.get(0).getVariableType().equals("boolean") ? userRolesDefinition.get(0).getVariableBoolean() :
//						userRolesDefinition.get(0).getVariableInteger());

		UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery()
				.eq(UserInfo::getTenantId,tenantId)
				.eq(UserInfo::getUserRole, userRolesDefinition.get(0).getVariableString())
				.like(UserInfo::getAffiliated, affiliationId));

		if (userInfo == null) return;

		UserInfoDto userInfoDto = new UserInfoDto();

		userInfoDto.setId(userInfo.getId());
		userInfoDto.setUserRole(userInfo.getUserRole());
		userInfoDto.setRealName(userInfo.getRealName());

		delegateExecution.setVariable("isValid", true);  // auto need
		delegateExecution.setVariable("directPush", directPushDefinition.get(0).getVariableBoolean().equals(1));
		delegateExecution.setVariable("manuallyCheckGuest", mcgDefinition.get(0).getVariableBoolean().equals(1));
		delegateExecution.setVariable("userInfoDto", userInfoDto);

		delegateExecution.setVariable("inviteDuration",inviteDDefinition.get(0).getVariableString());
		delegateExecution.setVariable("depositDuration",mcgDdepositDDefinition.get(0).getVariableString());

		delegateExecution.setVariable("executionDuration",executionDDefinition.get(0).getVariableString());

	}



	/**
	 * 从列表中找出包括Value值的列表
	 * @param list
	 * @param keyName
	 * @param value
	 * @return
	 */
	public static List<Map<String,Object>> findDataFromListMap(List<Map<String,Object>> list, String keyName, Object value) {

		Predicate<Map<String,Object>> predicate = new Predicate<Map<String,Object>>() {

			@Override
			public boolean evaluate(Map<String,Object> map) {
				// TODO Auto-generated method stub
				try {
					Object val = map.get(keyName);
					if(value == null || val == null)
						return false;

					return val.equals(value);
				}
				catch (Exception e) {
					return false;
				}
			}
		};

		List<Map<String,Object>> result = (List<Map<String,Object>>) CollectionUtils.select( list, predicate);
		return result;
	}


	/**
	 * 从列表中找出包括Value值的对象列表
	 * @param list
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static <T,V> List<T> findObjFromList(List<T> list,String fieldName,V value) {

		Predicate<T> predicate = new Predicate<T>() {

			@Override
			public boolean evaluate(T obj) {

				String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

				// TODO Auto-generated method stub
				try {
					Method method = obj.getClass().getMethod(methodName);
					@SuppressWarnings("unchecked")
					V keyName = (V) method.invoke(obj);
					return keyName.equals(value);
				} catch (Exception e) {
					return false;
				}
			}
		};

		List<T> result = (List<T>) CollectionUtils.select(list, predicate);
		return result;
	}


	// 从一个List<T>中，抽出T中指定项目（E类型），返回一个List<E>
    // srtList 抽出源List
    // tClass 源List中Item的类型（T自定义类）
    // eClass 源List中Item类中要抽出项目的类型E（Stringd等等）
    // fieldName 要抽出的项目名（注意，工具里面会利用这个fieldName生成对应的get方法,所以，该类中必须有对应的Get方法）
	public static <T, E> List<E> extractItemFromList(List<T> srtList, Class<T> tClass, Class<E> eClass, String fieldName) {
		List<E> rtnList = new ArrayList<E>();
		Method method = null;
		//获得方法名 getXXX
		String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		try {
			//通过给定的类名，生成类的实例，然后取得这个实例的指定方法名的引用。
			method = (tClass.newInstance()).getClass().getMethod(methodName);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		for (T t : srtList) {
			E obj = null;
			try {
				//执行对象t的特定方法（前面取得的方法名method）
				obj = (E) method.invoke(t);
				rtnList.add(obj);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return rtnList;
	}

}
