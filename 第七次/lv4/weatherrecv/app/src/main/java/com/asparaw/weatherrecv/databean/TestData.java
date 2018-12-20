package com.asparaw.weatherrecv.databean;

public class TestData {
    private static final String jsontest="{\"code\":200,\"msg\":\"成功!\",\"data\":{\"yesterday\":{\"date\":\"18日星期二\",\"high\":\"高温 11℃\",\"fx\":\"无持续风向\",\"low\":\"低温 6℃\",\"fl\":\"\\u003c![CDATA[\\u003c3级]]\\u003e\",\"type\":\"多云\"},\"city\":\"成都\",\"aqi\":\"192\",\"forecast\":[{\"date\":\"19日星期三\",\"high\":\"高温 10℃\",\"fengli\":\"\\u003c![CDATA[\\u003c3级]]\\u003e\",\"low\":\"低温 6℃\",\"fengxiang\":\"无持续风向\",\"type\":\"小雨\"},{\"date\":\"20日星期四\",\"high\":\"高温 10℃\",\"fengli\":\"\\u003c![CDATA[\\u003c3级]]\\u003e\",\"low\":\"低温 6℃\",\"fengxiang\":\"无持续风向\",\"type\":\"阴\"},{\"date\":\"21日星期五\",\"high\":\"高温 13℃\",\"fengli\":\"\\u003c![CDATA[\\u003c3级]]\\u003e\",\"low\":\"低温 6℃\",\"fengxiang\":\"无持续风向\",\"type\":\"晴\"},{\"date\":\"22日星期六\",\"high\":\"高温 12℃\",\"fengli\":\"\\u003c![CDATA[\\u003c3级]]\\u003e\",\"low\":\"低温 5℃\",\"fengxiang\":\"无持续风向\",\"type\":\"晴\"},{\"date\":\"23日星期天\",\"high\":\"高温 11℃\",\"fengli\":\"\\u003c![CDATA[\\u003c3级]]\\u003e\",\"low\":\"低温 5℃\",\"fengxiang\":\"无持续风向\",\"type\":\"多云\"}],\"ganmao\":\"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\",\"wendu\":\"8\"}}";
    private static final TestData instance=new TestData();
    public static String getJsontest() {
        return jsontest;
    }

    private TestData(){
        //do_nothing
    }
    public static TestData getInstance(){
        return instance;
    }
}
