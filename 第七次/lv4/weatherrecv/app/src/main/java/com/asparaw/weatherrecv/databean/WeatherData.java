package com.asparaw.weatherrecv.databean;

public class WeatherData {
    String city;
    String aqi;
    String ganmao;
    String wendu;

    public void setCity(String city) {
        this.city = city;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getCity() {
        return city;
    }

    public String getAqi() {
        return aqi;
    }

    public String getGanmao() {
        return ganmao;
    }

    public String getWendu() {
        return wendu;
    }
}
