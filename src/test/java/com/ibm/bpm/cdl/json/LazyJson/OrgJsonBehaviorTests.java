package com.ibm.bpm.cdl.json.LazyJson;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.json.*;
import org.junit.*;

import com.ibm.bpm.cdl.json.EazyJsonArr;
import com.ibm.bpm.cdl.json.EazyJsonObj;

public class OrgJsonBehaviorTests {
    
    public static final String xml_for_child_process_test = "<?xml version=\"1.0\" encoding=\"utf-8\"?><mon:monitorEvent mon:id=\"n14623b57f16081b51792162189\" xmlns:bpmn=\"http://schema.omg.org/spec/BPMN/2.0\" xmlns:bpmnx=\"http://www.ibm.com/xmlns/bpmnx/20100524/BusinessMonitoring\" xmlns:mon=\"http://www.ibm.com/xmlns/prod/websphere/monitoring/7.5\" xmlns:ibm=\"http://www.ibm.com/xmlns/prod/websphere/monitoring/7.5/extensions\" xmlns:wle=\"http://www.ibm.com/xmlns/prod/websphere/lombardi/7.5\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"><mon:eventPointData><mon:kind mon:version=\"2010-11-11\">bpmnx:ACTIVITY_COMPLETED</mon:kind><mon:time mon:of=\"occurrence\">2017-03-29T11:05:06.164+08:00</mon:time><ibm:sequenceId>0000000126</ibm:sequenceId><mon:model mon:type=\"bpmn:callActivity\" mon:id=\"c15f1744-91c5-40f5-840f-6024628db628\" mon:version=\"2064.a2c6c6e1-7908-4d0c-8584-278095c943fe\"><mon:name>链接子流程</mon:name><mon:instance mon:id=\"17\"></mon:instance></mon:model><mon:model mon:type=\"bpmn:subProcess\" mon:id=\"dfd4f9f4-9f2f-44f0-87f2-8703705c55a1\" mon:version=\"2064.a2c6c6e1-7908-4d0c-8584-278095c943fe\"><mon:name>子流程1</mon:name><mon:instance mon:id=\"8\"></mon:instance></mon:model><mon:model mon:type=\"bpmn:process\" mon:id=\"3bda4506-cd9f-4099-b901-d62358187afc\" mon:version=\"2064.a2c6c6e1-7908-4d0c-8584-278095c943fe\"><mon:name>父流程</mon:name><mon:documentation></mon:documentation><mon:instance mon:id=\"35863\"><mon:state>Active</mon:state></mon:instance></mon:model><mon:model mon:type=\"wle:processApplication\" mon:id=\"403d12ca-0f72-432f-9ba6-484cda744412\" mon:version=\"2064.a2c6c6e1-7908-4d0c-8584-278095c943fe\"><mon:name>测试event</mon:name><mon:documentation></mon:documentation></mon:model><mon:correlation><mon:ancestor mon:id=\"3bda4506-cd9f-4099-b901-d62358187afc.2064.a2c6c6e1-7908-4d0c-8584-278095c943fe.35863.17\"><mon:ancestor mon:id=\"3bda4506-cd9f-4099-b901-d62358187afc.2064.a2c6c6e1-7908-4d0c-8584-278095c943fe.35863.8\"><wle:internalSubProcessId>dfd4f9f4-9f2f-44f0-87f2-8703705c55a1</wle:internalSubProcessId><mon:ancestor mon:id=\"3bda4506-cd9f-4099-b901-d62358187afc.2064.a2c6c6e1-7908-4d0c-8584-278095c943fe.35863\"></mon:ancestor></mon:ancestor></mon:ancestor><wle:starting-process-instance>3bda4506-cd9f-4099-b901-d62358187afc.2064.a2c6c6e1-7908-4d0c-8584-278095c943fe.35863</wle:starting-process-instance></mon:correlation><mon:source><ibm:system ibm:systemID=\"9eaa8d5f-c6c3-4c3a-b0b7-d8133e6b8616\" /></mon:source></mon:eventPointData><mon:applicationData><wle:tracking-point wle:time=\"2017-03-29T11:05:06.164+08:00\" wle:name=\"链接子流程 (POST)\" wle:id=\"0e77915a17b34022-7fc3c15f1744-91c5-40f5-840f-6024628db628 (POST)\" wle:version=\"2064.a2c6c6e1-7908-4d0c-8584-278095c943fe\" wle:groupId=\"guid:abe88fb86b9f8479:-7620e779:15a17b34022:-7fc3\" wle:groupVersion=\"2064.a2c6c6e1-7908-4d0c-8584-278095c943fe\"><wle:kpi-data wle:name=\"Value Add\" wle:id=\"e30cf309-a884-4a7b-a2db-16e8a371a4c1\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">1</wle:kpi-data><wle:kpi-data wle:name=\"Total Time (Clock)\" wle:id=\"67cbb213-0032-4f14-be44-7e9c7a1a146f\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:dayTimeDuration\">P0DT0H0M0S</wle:kpi-data><wle:kpi-data wle:name=\"Resource Cost\" wle:id=\"d5da2c80-b2af-40a6-981d-9de4df12ed12\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">0</wle:kpi-data><wle:kpi-data wle:name=\"Labor Cost\" wle:id=\"fbec4968-5e4c-4f2b-b11b-f3c9ef63d09b\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">0</wle:kpi-data><wle:kpi-data wle:name=\"Rework\" wle:id=\"0f650e6c-a9d7-4355-90bd-06530fa3eeec\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">0</wle:kpi-data><wle:kpi-data wle:name=\"Wait Time (Clock)\" wle:id=\"43b503bd-63e7-4c42-8268-92d1033e0997\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:dayTimeDuration\">P0DT0H0M0S</wle:kpi-data><wle:kpi-data wle:name=\"Execution Time (Clock)\" wle:id=\"8601bb6b-9c9d-4cba-936e-16350a036de3\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:dayTimeDuration\">P0DT0H0M0S</wle:kpi-data><wle:kpi-data wle:name=\"Cost\" wle:id=\"995ba3fc-e786-45eb-b356-47acb3d3ebbc\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">0.00000000</wle:kpi-data></wle:tracking-point></mon:applicationData></mon:monitorEvent>";
    public static final String xml_tracking_point = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><mon:monitorEvent mon:id=\"S12251becb5daa51792162189\" xmlns:bpmn=\"http://schema.omg.org/spec/BPMN/2.0\" xmlns:bpmnx=\"http://www.ibm.com/xmlns/bpmnx/20100524/BusinessMonitoring\" xmlns:mon=\"http://www.ibm.com/xmlns/prod/websphere/monitoring/7.5\" xmlns:ibm=\"http://www.ibm.com/xmlns/prod/websphere/monitoring/7.5/extensions\" xmlns:wle=\"http://www.ibm.com/xmlns/prod/websphere/lombardi/7.5\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"><mon:eventPointData><mon:kind mon:version=\"2010-11-11\">bpmnx:ACTIVITY_RESOURCE_ASSIGNED</mon:kind><mon:time mon:of=\"occurrence\">2017-03-08T17:59:19.472+08:00</mon:time><ibm:sequenceId>0000000007</ibm:sequenceId><mon:model mon:type=\"bpmn:userTask\" mon:id=\"bpdid:431b0753c33842e2:3d5457c0:141a2fd3448:-75fb\" mon:version=\"2064.d3476112-f115-4d5a-9223-c4864fda84f3\"><mon:name>Submit position request</mon:name><mon:instance mon:id=\"3\"><mon:role mon:id=\"POTENTIAL_PERFORMER\"><mon:resource mon:id=\"HiringManagers_S_cd97937f-06ab-43cc-8067-17dea489fdb3.d3476112-f115-4d5a-9223-c4864fda84f3\"><mon:name>HiringManagers</mon:name><mon:documentation>HiringManagers</mon:documentation></mon:resource></mon:role><wle:taskInstanceId>334</wle:taskInstanceId></mon:instance><wle:snapshot-name>HSSMarch8</wle:snapshot-name></mon:model><mon:model mon:type=\"bpmn:process\" mon:id=\"c904b3b1-afc1-4698-bf5a-a20892c20275\" mon:version=\"2064.d3476112-f115-4d5a-9223-c4864fda84f3\"><mon:name>Standard HR Open New Position</mon:name><mon:documentation>This process covers a manager requesting to create a new position or fill an exisiting position. The process routes the postion request to the Human Resources (HR) department to search for candidates.</mon:documentation><mon:instance mon:id=\"322\"><mon:state>Active</mon:state></mon:instance><wle:snapshot-name>HSSMarch8</wle:snapshot-name></mon:model><mon:model mon:type=\"wle:processApplication\" mon:id=\"9ab0d0c6-d92c-4355-9ed5-d8a05acdc4b0\" mon:version=\"2064.d3476112-f115-4d5a-9223-c4864fda84f3\"><mon:name>Hiring Sample</mon:name><mon:documentation>Hiring Sample</mon:documentation><wle:snapshot-name>HSSMarch8</wle:snapshot-name></mon:model><mon:correlation><mon:ancestor mon:id=\"c904b3b1-afc1-4698-bf5a-a20892c20275.2064.d3476112-f115-4d5a-9223-c4864fda84f3.322.3\"><mon:ancestor mon:id=\"c904b3b1-afc1-4698-bf5a-a20892c20275.2064.d3476112-f115-4d5a-9223-c4864fda84f3.322\"></mon:ancestor></mon:ancestor><wle:starting-process-instance>c904b3b1-afc1-4698-bf5a-a20892c20275.2064.d3476112-f115-4d5a-9223-c4864fda84f3.322</wle:starting-process-instance></mon:correlation><mon:source><ibm:system ibm:systemID=\"9eaa8d5f-c6c3-4c3a-b0b7-d8133e6b8616\"/></mon:source></mon:eventPointData><mon:applicationData><wle:tracking-point wle:time=\"2017-03-08T17:59:19.472+08:00\" wle:name=\"Submit position request\" wle:id=\"4-aa5a-f4ed8784d4a8bpdid431b0753c33842e23d5457c0141a2fd3448-75fb\" wle:version=\"2064.d3476112-f115-4d5a-9223-c4864fda84f3\" wle:groupName=\"aEmpRequisition121381434563922\" wle:groupId=\"19b73424-1bdc-4ee4-aa5a-f4ed8784d4a8\" wle:groupVersion=\"2064.d3476112-f115-4d5a-9223-c4864fda84f3\"><wle:tracked-field wle:name=\"HiringManager\" wle:id=\"bpdid:431b0753c33842e2:3d5457c0:141a2fd3448:-761c\" wle:type=\"xs:string\"></wle:tracked-field><wle:tracked-field wle:name=\"EmploymentStatus\" wle:id=\"bpdid:431b0753c33842e2:3d5457c0:141a2fd3448:-761b\" wle:type=\"xs:string\"></wle:tracked-field><wle:tracked-field wle:name=\"Department\" wle:id=\"bpdid:431b0753c33842e2:3d5457c0:141a2fd3448:-761a\" wle:type=\"xs:string\"></wle:tracked-field><wle:tracked-field wle:name=\"Location\" wle:id=\"bpdid:431b0753c33842e2:3d5457c0:141a2fd3448:-7619\" wle:type=\"xs:string\"></wle:tracked-field><wle:tracked-field wle:name=\"GMApproval\" wle:id=\"bpdid:431b0753c33842e2:3d5457c0:141a2fd3448:-7618\" wle:type=\"xs:string\">false</wle:tracked-field><wle:kpi-data wle:name=\"Labor Cost\" wle:id=\"fbec4968-5e4c-4f2b-b11b-f3c9ef63d09b\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">0</wle:kpi-data><wle:kpi-data wle:name=\"Rework\" wle:id=\"0f650e6c-a9d7-4355-90bd-06530fa3eeec\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">0</wle:kpi-data><wle:kpi-data wle:name=\"Wait Time (Clock)\" wle:id=\"43b503bd-63e7-4c42-8268-92d1033e0997\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:dayTimeDuration\">P0DT0H0M0S</wle:kpi-data><wle:kpi-data wle:name=\"Execution Time (Clock)\" wle:id=\"8601bb6b-9c9d-4cba-936e-16350a036de3\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:dayTimeDuration\">P0DT0H0M0S</wle:kpi-data><wle:kpi-data wle:name=\"Value Add\" wle:id=\"e30cf309-a884-4a7b-a2db-16e8a371a4c1\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">1</wle:kpi-data><wle:kpi-data wle:name=\"Resource Cost\" wle:id=\"d5da2c80-b2af-40a6-981d-9de4df12ed12\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">0</wle:kpi-data><wle:kpi-data wle:name=\"Total Time (Clock)\" wle:id=\"67cbb213-0032-4f14-be44-7e9c7a1a146f\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:dayTimeDuration\">P0DT0H0M0S</wle:kpi-data><wle:kpi-data wle:name=\"Cost\" wle:id=\"995ba3fc-e786-45eb-b356-47acb3d3ebbc\" wle:version=\"2064.1080ded6-d153-4654-947c-2d16fce170ed\" wle:type=\"xs:decimal\">0.00000000</wle:kpi-data></wle:tracking-point></mon:applicationData></mon:monitorEvent>";
	@Test
	public void testJson(){
		JSONObject obj = new JSONObject("{\"sda\":[true,\"ceawe\",[123,123,123,{\"dddd\"   \t:\"dddd\",\"number\":123123}]],\"dddd\"   \t:\"dddd\",\"number\":123123}");
	}
	
	@Test
	public void testNewEazy() throws IOException{
	    EazyJsonObj ddd1 = new EazyJsonObj("{\"sda\":[   null  ,\"ceawe\",[\t\t\n123 ,1.23,-12.3,{\"dddd\"   \t:\"dddd\",\"number\":123123}]],\"dddd\"   \t:\"dddd\",\"number\":1234567890123456,\"boolean\":true}");
	    EazyJsonArr arr = (EazyJsonArr) ddd1.optGet("sda");
	    arr = (EazyJsonArr) arr.get(2);
	    //System.out.println(arr.get(2).getClass().getName());
	    assertEquals(-12.3f,arr.get(2));
	    ddd1 = (EazyJsonObj) arr.get(3);
	    assertEquals(123123,ddd1.optGet("number"));
	}
	
	@Test
	public void testMad() throws IOException{
	    /*org.json.JSONObject obj = org.json.XML.toJSONObject(xml_tracking_point);
	    String jsonStr = obj.toString();
	    
	    Date start = new Date();
	    for(int i =0;i<20000;i++){
	        obj = new org.json.JSONObject(jsonStr);
	        obj.getJSONObject("mon:monitorEvent").get("mon:eventPointData").toString();
	    }
	    Date end = new Date();
	    System.out.println("Time is:" + (end.getTime() - start.getTime()));
	    
	    start = new Date();
        for(int i =0;i<20000;i++){
            EazyJsonObj obj1 = new EazyJsonObj(jsonStr);
            obj1.optGetEazyJsonObj("mon:monitorEvent").optGet("mon:eventPointData").toString();
        }
        end = new Date();
        System.out.println("Time is:" + (end.getTime() - start.getTime()));*/
	}
	
	@Test
	public void testHello(){
	    org.json.JSONObject obj = new org.json.JSONObject();
	    obj.put("test", new OrgJsonBehaviorTests());
	    System.out.println(obj);
	}
}
