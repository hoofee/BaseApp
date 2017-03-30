package com.hoofee.everything.main.model;

import com.hoofee.everything.main.model.base.BaseModel;

import java.util.List;

/**
 * Created by hufei on 2016/9/12.
 */
public class ArticleSectionModel extends BaseModel {


    /**
     * sectionId : 41
     * sectionName : 今日动态
     * sectionOrder : 0
     * articleList : [
     * {"sectionId":41,"sectionName":"今日动态","sectionOrder":0,"id":268,"topPhoto":null,"name":"图册图册","type":2,
     * "description":"123","author":"深圳市血液中心","photo":"/content/0/album/146949839123661195.png",
     * "detail":null,"hints":56,"commentCount":0,"createTime":1469170619,"updateTime":1469700528,
     * "shareLink":"http://bs.sharing8.cn/manage/office/article/detail?articleId=268",
     * "albumPhotoList":[
     * {"id":8,"name":null,"filePath":"/content/0/album/146949841058497546.png","thumbFilePath":null,"description":"（一）5年内，可享用献血量3倍的血量n12.\n13.","createTime":1469700528},
     * {"id":9,"name":null,"filePath":"/content/0/album/146949842415558398.png","thumbFilePath":null,"description":"（一）5年内，）5年内，献","createTime":1469700528},
     * {"id":10,"name":null,"filePath":"/content/0/album/146949844204168028.png","thumbFilePath":null,"description":"（一）5年内，可享用献0\n地方 地","createTime":1469700528}
     * ],
     * "isTopOne":false
     * }
     * ]
     */

    private long                  sectionId;
    private String                sectionName;
    private long                  sectionOrder;
    /**
     * sectionId : 41
     * sectionName : 今日动态
     * sectionOrder : 0
     * id : 268
     * topPhoto : null
     * name : 图册图册
     * type : 2
     * description : 123
     * author : 深圳市血液中心
     * photo : /content/0/album/146949839123661195.png
     * detail : null
     * hints : 56
     * commentCount : 0
     * createTime : 1469170619
     * updateTime : 1469700528
     * shareLink : http://bs.sharing8.cn/manage/office/article/detail?articleId=268
     * albumPhotoList : [{"id":8,"name":null,"filePath":"/content/0/album/146949841058497546.png","thumbFilePath":null,"description":"（一）5年内，可享用献血量3倍的血量。\n（二）5年后，可享用献血量等量的血量。\n（三）5年内，献血量累\n（四）5年内，献血量累\n（五）5年内，献\n6.\n8.\n6.\n7.\n8.\n9.\n10.\n11.\n12.\n13.","createTime":1469700528},{"id":9,"name":null,"filePath":"/content/0/album/146949842415558398.png","thumbFilePath":null,"description":"（一）5年内，可享用献血量3倍的血量。\n（二）5年后，可享用献血量等量的血量。\n（三）5年内，献量累计满600毫升以上的，10年内免费享用所需血量。\n（四）5年内，献血量累计满800毫升以上的，15年内免费享用所需血量。\n（五）5年内，献","createTime":1469700528},{"id":10,"name":null,"filePath":"/content/0/album/146949844204168028.png","thumbFilePath":null,"description":"（一）5年内，可享用献血量3倍的血量。\n（二）5年后，可享用献血量等量的血量。\n（三）5年内，献血量累计满600毫升以上的\n10年内免费享用所需血量。\n（四）5年内，献血量累计满800毫升以上\n15年内免费享用所需血量。\n地方 地","createTime":1469700528}]
     * isTopOne : false
     */

    private List<ArticleListBean> articleList;

    //---------------------------------------------------

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public long getSectionOrder() {
        return sectionOrder;
    }

    public void setSectionOrder(long sectionOrder) {
        this.sectionOrder = sectionOrder;
    }

    public List<ArticleListBean> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleListBean> articleList) {
        this.articleList = articleList;
    }

    public static class ArticleListBean extends BaseModel {
        private long                     sectionId;
        private String                   sectionName;
        private int                      sectionOrder;
        private long                     id;
        private String                   topPhoto;
        private String                   name;
        private int                      type;
        private String                   description;
        private String                   author;
        private String                   photo;
        private String                   detail;
        private long                     hints;
        private long                     commentCount;
        private long                     createTime;
        private long                     updateTime;
        private String                   shareLink;
        private boolean                  isTopOne;
        /**
         * id : 8
         * name : null
         * filePath : /content/0/album/146949841058497546.png
         * thumbFilePath : null
         * description : （一）5年内，可享用献血量3倍的血量。
         * （二）5年后，可享用献血量等量的血量。
         * createTime : 1469700528
         */

        private List<AlbumPhotoListBean> albumPhotoList;

        public long getSectionId() {
            return sectionId;
        }

        public void setSectionId(long sectionId) {
            this.sectionId = sectionId;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public int getSectionOrder() {
            return sectionOrder;
        }

        public void setSectionOrder(int sectionOrder) {
            this.sectionOrder = sectionOrder;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTopPhoto() {
            return topPhoto;
        }

        public void setTopPhoto(String topPhoto) {
            this.topPhoto = topPhoto;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public Object getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getHints() {
            return hints;
        }

        public void setHints(long hints) {
            this.hints = hints;
        }

        public long getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(long commentCount) {
            this.commentCount = commentCount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getShareLink() {
            return shareLink;
        }

        public void setShareLink(String shareLink) {
            this.shareLink = shareLink;
        }

        public boolean isIsTopOne() {
            return isTopOne;
        }

        public void setIsTopOne(boolean isTopOne) {
            this.isTopOne = isTopOne;
        }

        public List<AlbumPhotoListBean> getAlbumPhotoList() {
            return albumPhotoList;
        }

        public void setAlbumPhotoList(List<AlbumPhotoListBean> albumPhotoList) {
            this.albumPhotoList = albumPhotoList;
        }

        @Override
        public String toString() {
            return "ArticleListBean{" +
                    "albumPhotoList=" + albumPhotoList +
                    ", sectionId=" + sectionId +
                    ", sectionName='" + sectionName + '\'' +
                    ", sectionOrder=" + sectionOrder +
                    ", id=" + id +
                    ", topPhoto='" + topPhoto + '\'' +
                    ", name='" + name + '\'' +
                    ", type=" + type +
                    ", description='" + description + '\'' +
                    ", author='" + author + '\'' +
                    ", photo='" + photo + '\'' +
                    ", hints=" + hints +
                    ", commentCount=" + commentCount +
                    ", createTime=" + createTime +
                    ", updateTime=" + updateTime +
                    ", shareLink='" + shareLink + '\'' +
                    ", isTopOne=" + isTopOne +
                    '}';
        }
    }

    public static class AlbumPhotoListBean extends BaseModel {
        private int    id;
        private String name;
        private String filePath;
        private String thumbFilePath;
        private String description;
        private long   createTime;

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

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getThumbFilePath() {
            return thumbFilePath;
        }

        public void setThumbFilePath(String thumbFilePath) {
            this.thumbFilePath = thumbFilePath;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }

    @Override
    public String toString() {
        return "ArticleSectionModel{" +
                "articleList=" + articleList.toString() +
                ", sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", sectionOrder=" + sectionOrder +
                '}';
    }
}
