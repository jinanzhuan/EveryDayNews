package com.guigu.test.everydaynews.bean;

import java.util.List;

/**
 * Created by shuwei on 2016/8/10.
 */
public class NewsCenterBean {
    private List<NewsCenterPagerData> data;
    private List extend;
    private int retcode;


    public static class NewsCenterPagerData {
        private List<ChildrenData> children;
        private int id;
        private String title;
        private int type;
        private String url;
        private String url1;
        private String dayurl;
        private String excurl;
        private String weekurl;

        public static class ChildrenData{
            private int id;
            private String title;
            private int type;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "ChildrenData{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", type=" + type +
                        ", url='" + url + '\'' +
                        '}';
            }
        }

        public List<ChildrenData> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenData> children) {
            this.children = children;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl1() {
            return url1;
        }

        public void setUrl1(String url1) {
            this.url1 = url1;
        }

        public String getDayurl() {
            return dayurl;
        }

        public void setDayurl(String dayurl) {
            this.dayurl = dayurl;
        }

        public String getExcurl() {
            return excurl;
        }

        public void setExcurl(String excurl) {
            this.excurl = excurl;
        }

        public String getWeekurl() {
            return weekurl;
        }

        public void setWeekurl(String weekurl) {
            this.weekurl = weekurl;
        }

        @Override
        public String toString() {
            return "NewsCenterPagerData{" +
                    "children=" + children +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    ", url1='" + url1 + '\'' +
                    ", dayurl='" + dayurl + '\'' +
                    ", excurl='" + excurl + '\'' +
                    ", weekurl='" + weekurl + '\'' +
                    '}';
        }
    }

    public List<NewsCenterPagerData> getData() {
        return data;
    }

    public void setData(List<NewsCenterPagerData> data) {
        this.data = data;
    }

    public List getExtend() {
        return extend;
    }

    public void setExtend(List extend) {
        this.extend = extend;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    @Override
    public String toString() {
        return "NewsCenterPagerBean{" +
                "data=" + data +
                ", extend=" + extend +
                ", retcode=" + retcode +
                '}';
    }
}
