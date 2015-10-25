package com.policedata.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Objects {
	public static class Priorities 
	{
		 @SerializedName("issue")
		String issueStr;
		 @SerializedName("issue-date")
		String issueDateStr;
		@SerializedName("action")
		String actionStr;
		@SerializedName("action-date")
		String actionDateStr;
		
		
		public String getIssueStr() {
			return issueStr;
		}
		public void setIssueStr(String issueStr) {
			this.issueStr = issueStr;
		}
		public String getIssueDateStr() {
			return issueDateStr;
		}
		public void setIssueDateStr(String issueDateStr) {
			this.issueDateStr = issueDateStr;
		}
		public String getActionStr() {
			return actionStr;
		}
		public void setActionStr(String actionStr) {
			this.actionStr = actionStr;
		}
		public String getActionDateStr() {
			return actionDateStr;
		}
		public void setActionDateStr(String actionDateStr) {
			this.actionDateStr = actionDateStr;
		}
	}
	
	public static class CrimeCategories
	{
		 @SerializedName("url")
		String uniqueID;
		 @SerializedName("name")
		String crimeName;
		public String getUniqueID() {
			return uniqueID;
		}
		public void setUniqueID(String uniqueID) {
			this.uniqueID = uniqueID;
		}
		public String getCrimeName() {
			return crimeName;
		}
		public void setCrimeName(String crimeName) {
			this.crimeName = crimeName;
		}
	}

	public static class CrimesAtLocation
	{
		 @SerializedName("category")
		String category;
		 
		 @SerializedName("persistent_id")
		String uniqueCrimeID;
		 @SerializedName("month")
		String monthOfCrime;
		 @SerializedName("location")
		 JsonObject locationObject;
		 @SerializedName("date")
		String dateOfCrime;
		 @SerializedName("longitude")
		String longitude;
		 @SerializedName("latitude")
		String latitude;
		 //@SerializedName("street")
		 //JsonObject streetObject;
		 //@SerializedName("outcome_status")
		 //JsonObject outcomeObject;
		 
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getUniqueCrimeID() {
			return uniqueCrimeID;
		}
		public void setUniqueCrimeID(String uniqueCrimeID) {
			this.uniqueCrimeID = uniqueCrimeID;
		}
		public String getMonthOfCrime() {
			return monthOfCrime;
		}
		public void setMonthOfCrime(String monthOfCrime) {
			this.monthOfCrime = monthOfCrime;
		}
		public JsonObject getLocationOfCrime() {
			return locationObject;
		}
		public void setLocationOfCrime(JsonObject locationOfCrime) {
			this.locationObject = locationOfCrime;
		}
		public String getDateOfCrime() {
			return dateOfCrime;
		}
		public void setDateOfCrime(String dateOfCrime) {
			this.dateOfCrime = dateOfCrime;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
	}
	
	public static class NeighbourhoodTeam
	{
		 @SerializedName("bio")
		String Biogoraphy;
		 @SerializedName("name")
		String officerName; 
		 @SerializedName("rank")
		String officerRank;
		 @SerializedName("contact_details")
		String contactDetails;
		public String getBiogoraphy() {
			return Biogoraphy;
		}
		public void setBiogoraphy(String biogoraphy) {
			Biogoraphy = biogoraphy;
		}
		public String getOfficerName() {
			return officerName;
		}
		public void setOfficerName(String officerName) {
			this.officerName = officerName;
		}
		public String getOfficerRank() {
			return officerRank;
		}
		public void setOfficerRank(String officerRank) {
			this.officerRank = officerRank;
		}
		public String getContactDetails() {
			return contactDetails;
		}
		public void setContactDetails(String contactDetails) {
			this.contactDetails = contactDetails;
		}
		 

	}
}

