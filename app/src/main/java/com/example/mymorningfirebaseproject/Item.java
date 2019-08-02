package com.example.mymorningfirebaseproject;

public class Item {
        String mName,mEmail,mPass,mId;

        public Item(String mName, String mEmail, String mPass, String mId) {
            this.mName = mName;
            this.mEmail = mEmail;
            this.mPass = mPass;
            this.mId = mId;
        }
        //Needs 2 constrcutors
        public Item() {
        }
//Halfu getter and setter

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmEmail() {
            return mEmail;
        }

        public void setmEmail(String mEmail) {
            this.mEmail = mEmail;
        }

        public String getmPass() {
            return mPass;
        }

        public void setmPass(String mPass) {
            this.mPass = mPass;
        }

        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }
    }



