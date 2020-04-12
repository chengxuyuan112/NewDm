package com.xteamsoft.digitalpumper.bean;

import java.io.Serializable;

public class MessageBean implements Serializable {
    private String no_read;

    private Object rows;

    private String total;

    public String getNo_read() {
        return this.no_read;
    }

    public Object getRows() {
        return this.rows;
    }

    public String getTotal() {
        return this.total;
    }

    public void setNo_read(String paramString) {
        this.no_read = paramString;
    }

    public void setRows(Object paramObject) {
        this.rows = paramObject;
    }

    public void setTotal(String paramString) {
        this.total = paramString;
    }

    public static class MessageBeanContent implements Serializable {
        private String content;

        private String create_time;

        private String id;

        private String is_read;

        private String title;

        private String type;

        public String getContent() {
            return this.content;
        }

        public String getCreate_time() {
            return this.create_time;
        }

        public String getId() {
            return this.id;
        }

        public String getIs_read() {
            return this.is_read;
        }

        public String getTitle() {
            return this.title;
        }

        public String getType() {
            return this.type;
        }

        public void setContent(String param1String) {
            this.content = param1String;
        }

        public void setCreate_time(String param1String) {
            this.create_time = param1String;
        }

        public void setId(String param1String) {
            this.id = param1String;
        }

        public void setIs_read(String param1String) {
            this.is_read = param1String;
        }

        public void setTitle(String param1String) {
            this.title = param1String;
        }

        public void setType(String param1String) {
            this.type = param1String;
        }
    }
}
