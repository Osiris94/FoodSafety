package com.osiris.food.model;

public class Login {


    /**
     * status : success
     * code : 200
     * data : {"token_type":"Bearer","expires_in":1296000,"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFjZTEyMzJiMWRjNjhiNGIzMzljODY2MmVkNTg2NDNhYTVhMjA0Yjg1MmE5ODJiNjkyMDNjOTM0ZjY5ODQzMWMyNWJhNDM4OWMzZDIzZDlhIn0.eyJhdWQiOiIyIiwianRpIjoiYWNlMTIzMmIxZGM2OGI0YjMzOWM4NjYyZWQ1ODY0M2FhNWEyMDRiODUyYTk4MmI2OTIwM2M5MzRmNjk4NDMxYzI1YmE0Mzg5YzNkMjNkOWEiLCJpYXQiOjE1NjExOTE3MzEsIm5iZiI6MTU2MTE5MTczMSwiZXhwIjoxNTYyNDg3NzMxLCJzdWIiOiIxIiwic2NvcGVzIjpbIioiXX0.axxhJPML31UPJnRi_zhF2-nnsfeAAenu_z4_UsM91BBA8CA-9Nedyss54xK6f4rghAbjLARCQ6DUeERYwbEzfcjnihO0l-p-NgCHdFpiSRI-X3GEA0aztw3hCRgf_qV8m8UwhbahiV8wOrYUZGcti5Hw4ftjO8qWlcsDIpDIX_mUB5YNiqS4JgnSoNiptZXO7-Mxs_5wLHApaAbRdv4oPgfC-pyrXrod5hmrUiOb_oGxwS_8yai3FEMtS-T3sMjFQpNDaZbwXVrVzON_RrJ2R1jNi4SW_cCKSHYMYQxxqHDP80iZheIbelvpnaZaaaHhMVcK-52D5zsssrrirXHZ2xUz2Xe4MSfP_1_GKj8EvfOCDMYZIDXz58N-UMlBHNoLy-SvwHUB5HSuD1skwmki_Z0jlp1d_wNMKclNASaFjocPc9LgGV-q-Ctx1IH2hmsSnvcB8Qjrpr5nnS2aDzBWc0ssCaH7DITo1HuW2oaQ7DJ62yS30MzVZWsKw62rt-ppfXVhUtgH2N2Nz1jN1Z5rZfH9felbuYB3aTV-7E0EmGWvhvDhVyybJ0Bnhook_SGfWNC0ccFNh-7pEDxUfTYKJbz5_zOyoq_1k0CWTsINPYGJlGPm_GShbTVf4VoEGHr8YG6Yntt6SJOb4nN2StSGDh8nm9HlBYBPuUbkGugUu1w","refresh_token":"def5020095cc187c2b8f85d17c79f9c56d4b096ff7ac633b07ad4e9073051e655255394f8a036fc7c49dbe2d8a0da4bd05e591b871dbd5396f479f6d1096191543da0a5cf1d4b471f3aa6825c5f3ea478501b3fa83b3106de5880dbd848513a2b99508f7f69eb6acd7ee1dabba6a7d368441c7478997674c04fb4d3a4b070e66ff6fef728d9b5a248ab121e24d8c7589b5ec4d89ac909947ffe57ba7cb500778b660e77a0bcc39bb6967a955c5fe9b8e155032f6627723b1e623def5a3556cf2481121b27af29634e591b91f2fbdfdd706e5ba79b9e39d8c5af2b52f4e9365eb46c537db33440325457fe9355796f332cd114bc7aad462940521fdd64b0d5bdce78bb60aedf5c99cce2a07871c4fd537a5658a7bf6251d91db732fa355f161f3ff318723bf0b55f3a68961d9c9f4a225b1e02374b4e6863f89dbecb7d951d8eb7d70321b2a5bfb2874c596c73d9ae3c80f3e291f7b354143d1030538c2518c213c07ed8d","id":1,"name":"charles","status":"正常","created_at":"1985-02-16 05:39:32","updated_at":"1985-02-16 05:39:32"}
     */

    private String status;
    private int code;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token_type : Bearer
         * expires_in : 1296000
         * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFjZTEyMzJiMWRjNjhiNGIzMzljODY2MmVkNTg2NDNhYTVhMjA0Yjg1MmE5ODJiNjkyMDNjOTM0ZjY5ODQzMWMyNWJhNDM4OWMzZDIzZDlhIn0.eyJhdWQiOiIyIiwianRpIjoiYWNlMTIzMmIxZGM2OGI0YjMzOWM4NjYyZWQ1ODY0M2FhNWEyMDRiODUyYTk4MmI2OTIwM2M5MzRmNjk4NDMxYzI1YmE0Mzg5YzNkMjNkOWEiLCJpYXQiOjE1NjExOTE3MzEsIm5iZiI6MTU2MTE5MTczMSwiZXhwIjoxNTYyNDg3NzMxLCJzdWIiOiIxIiwic2NvcGVzIjpbIioiXX0.axxhJPML31UPJnRi_zhF2-nnsfeAAenu_z4_UsM91BBA8CA-9Nedyss54xK6f4rghAbjLARCQ6DUeERYwbEzfcjnihO0l-p-NgCHdFpiSRI-X3GEA0aztw3hCRgf_qV8m8UwhbahiV8wOrYUZGcti5Hw4ftjO8qWlcsDIpDIX_mUB5YNiqS4JgnSoNiptZXO7-Mxs_5wLHApaAbRdv4oPgfC-pyrXrod5hmrUiOb_oGxwS_8yai3FEMtS-T3sMjFQpNDaZbwXVrVzON_RrJ2R1jNi4SW_cCKSHYMYQxxqHDP80iZheIbelvpnaZaaaHhMVcK-52D5zsssrrirXHZ2xUz2Xe4MSfP_1_GKj8EvfOCDMYZIDXz58N-UMlBHNoLy-SvwHUB5HSuD1skwmki_Z0jlp1d_wNMKclNASaFjocPc9LgGV-q-Ctx1IH2hmsSnvcB8Qjrpr5nnS2aDzBWc0ssCaH7DITo1HuW2oaQ7DJ62yS30MzVZWsKw62rt-ppfXVhUtgH2N2Nz1jN1Z5rZfH9felbuYB3aTV-7E0EmGWvhvDhVyybJ0Bnhook_SGfWNC0ccFNh-7pEDxUfTYKJbz5_zOyoq_1k0CWTsINPYGJlGPm_GShbTVf4VoEGHr8YG6Yntt6SJOb4nN2StSGDh8nm9HlBYBPuUbkGugUu1w
         * refresh_token : def5020095cc187c2b8f85d17c79f9c56d4b096ff7ac633b07ad4e9073051e655255394f8a036fc7c49dbe2d8a0da4bd05e591b871dbd5396f479f6d1096191543da0a5cf1d4b471f3aa6825c5f3ea478501b3fa83b3106de5880dbd848513a2b99508f7f69eb6acd7ee1dabba6a7d368441c7478997674c04fb4d3a4b070e66ff6fef728d9b5a248ab121e24d8c7589b5ec4d89ac909947ffe57ba7cb500778b660e77a0bcc39bb6967a955c5fe9b8e155032f6627723b1e623def5a3556cf2481121b27af29634e591b91f2fbdfdd706e5ba79b9e39d8c5af2b52f4e9365eb46c537db33440325457fe9355796f332cd114bc7aad462940521fdd64b0d5bdce78bb60aedf5c99cce2a07871c4fd537a5658a7bf6251d91db732fa355f161f3ff318723bf0b55f3a68961d9c9f4a225b1e02374b4e6863f89dbecb7d951d8eb7d70321b2a5bfb2874c596c73d9ae3c80f3e291f7b354143d1030538c2518c213c07ed8d
         * id : 1
         * name : charles
         * status : 正常
         * created_at : 1985-02-16 05:39:32
         * updated_at : 1985-02-16 05:39:32
         */

        private String token_type;
        private int expires_in;
        private String access_token;
        private String refresh_token;
        private int id;
        private String name;
        private String status;
        private String created_at;
        private String updated_at;
        private String version;
        private String downloadUrl;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
