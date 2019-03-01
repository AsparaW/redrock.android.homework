package com.mantouland.fakezhihuribao.bean;

import java.util.List;

public class HotNew {
    /**
     * date : 20190224
     * stories : [{"title":"你要说奥斯卡的这个奖，我可不困了啊","ga_prefix":"022410","images":["https://pic4.zhimg.com/v2-80bd780f564ee50667b3122b249409fb.jpg"],"multipic":true,"type":0,"id":9705402},{"images":["https://pic2.zhimg.com/v2-0e7a8965822102e37aecabf9683d99bd.jpg"],"type":0,"id":9708013,"ga_prefix":"022409","title":"拥有更大的脑子，反而不能笑到最后"},{"images":["https://pic2.zhimg.com/v2-d4d368db5862972b83d500525b2d8089.jpg"],"type":0,"id":9707814,"ga_prefix":"022408","title":"美国为什么没有大型公务员招聘考试？因为没必要啊"},{"title":"豆瓣挨了用户的「板子」，冤吗？","ga_prefix":"022407","images":["https://pic1.zhimg.com/v2-8f2fce3560afa7374f2918d6a03574b0.jpg"],"multipic":true,"type":0,"id":9707969},{"images":["https://pic2.zhimg.com/v2-dc043053ec45f92203701c744e1d3a19.jpg"],"type":0,"id":9707949,"ga_prefix":"022406","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-505435a2c77467a00dad552539db041d.jpg","type":0,"id":9707969,"ga_prefix":"022407","title":"豆瓣挨了用户的「板子」，冤吗？"},{"image":"https://pic3.zhimg.com/v2-51704c44b82eae6003a5b8162a9fd82e.jpg","type":0,"id":9707787,"ga_prefix":"022321","title":"《阿丽塔》的背后，站着卡神和 12 岁就想拍电影的鬼才导演"},{"image":"https://pic1.zhimg.com/v2-817098e49827b739ee0ba00e89623520.jpg","type":0,"id":9707974,"ga_prefix":"022310","title":"iPhone 相机完全操作指南（40000 字）"},{"image":"https://pic3.zhimg.com/v2-be1e59aa842b2120924ba7f002734b0a.jpg","type":0,"id":9707772,"ga_prefix":"022221","title":"别瞪着我，你这个长得人模狗样的家伙"},{"image":"https://pic4.zhimg.com/v2-efab9b245f323379f2d4020140ae1c4f.jpg","type":0,"id":9707954,"ga_prefix":"022308","title":"千古未解之谜之小猪佩奇那是正脸还是侧脸啊？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * title : 你要说奥斯卡的这个奖，我可不困了啊
         * ga_prefix : 022410
         * images : ["https://pic4.zhimg.com/v2-80bd780f564ee50667b3122b249409fb.jpg"]
         * multipic : true
         * type : 0
         * id : 9705402
         */

        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-505435a2c77467a00dad552539db041d.jpg
         * type : 0
         * id : 9707969
         * ga_prefix : 022407
         * title : 豆瓣挨了用户的「板子」，冤吗？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

