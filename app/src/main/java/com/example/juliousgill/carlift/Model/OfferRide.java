package com.example.juliousgill.carlift.Model;

/**
 * Created by Julious Gulshan - 15969
 */

public class OfferRide {

    //variables
    String offerRideId;
    String offerSourceTxt;
    String offerDestinationTxt;
    String offerTimeTxt;
    String offerPhoneTxt;
    String offerCarTxt;

    //constructor for reading the values
    public OfferRide(){ }

    public OfferRide(String offerRideId, String offerSourceTxt, String offerDestinationTxt, String offerTimeTxt, String offerPhoneTxt, String offerCarTxt) {
        this.offerRideId = offerRideId;
        this.offerSourceTxt = offerSourceTxt;
        this.offerDestinationTxt = offerDestinationTxt;
        this.offerTimeTxt = offerTimeTxt;
        this.offerPhoneTxt = offerPhoneTxt;
        this.offerCarTxt = offerCarTxt;
    }

    //setter and getter
    public String getOfferRideId() {
        return offerRideId;
    }

    public void setOfferRideId(String offerRideId) {
        this.offerRideId = offerRideId;
    }

    public String getOfferSourceTxt() {
        return offerSourceTxt;
    }

    public void setOfferSourceTxt(String offerSourceTxt) {
        this.offerSourceTxt = offerSourceTxt;
    }

    public String getOfferDestinationTxt() {
        return offerDestinationTxt;
    }

    public void setOfferDestinationTxt(String offerDestinationTxt) {
        this.offerDestinationTxt = offerDestinationTxt;
    }

    public String getOfferTimeTxt() {
        return offerTimeTxt;
    }

    public void setOfferTimeTxt(String offerTimeTxt) {
        this.offerTimeTxt = offerTimeTxt;
    }

    public String getOfferPhoneTxt() {
        return offerPhoneTxt;
    }

    public void setOfferPhoneTxt(String offerPhoneTxt) {
        this.offerPhoneTxt = offerPhoneTxt;
    }

    public String getOfferCarTxt() {
        return offerCarTxt;
    }

    public void setOfferCarTxt(String offerCarTxt) {
        this.offerCarTxt = offerCarTxt;
    }
}
