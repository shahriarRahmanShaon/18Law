package com.ovi.a16flawbd.ModelClasses;

public class Appointment_info {
    private String got_appointment, which_lawyer;


    public Appointment_info(String got_appointment, String which_lawyer) {
        this.got_appointment = got_appointment;
        this.which_lawyer = which_lawyer;
    }

    public String getGot_appointment() {
        return got_appointment;
    }

    public void setGot_appointment(String got_appointment) {
        this.got_appointment = got_appointment;
    }

    public String getWhich_lawyer() {
        return which_lawyer;
    }

    public void setWhich_lawyer(String which_lawyer) {
        this.which_lawyer = which_lawyer;
    }

    public Appointment_info() {

    }
}
