package com.hecai.service.test.dao;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ToString
@TableName("info_file")
public class InfoFile implements Serializable,Cloneable{
    /** 文件id */
    @TableId(value = "file_id")
    private Integer fileId ;
    /** 文件组id;uuid */
    private String fileGroupId ;
    /** 旧文件名;上传时的文件名 */
    private String nameOld ;
    /** 新文件名;重命名后的文件名 */
    private String nameNew ;
    /** 文件格式 */
    private String format ;
    /** 文件大小 */
    @Max(value = 2,message = "size不能大于2")
    @NotNull
    private Long size ;
    /** 实际路径 */
    private String uri ;
    /** 网络路径 */
    private String url ;
    /** 创建人 */
    private Integer createBy ;
    /** 创建时间 */
    private Date createTime ;

    /** 文件id */
    public Integer getFileId(){
        return this.fileId;
    }
    /** 文件id */
    public void setFileId(Integer fileId){
        this.fileId = fileId;
    }
    /** 文件组id;uuid */
    public String getFileGroupId(){
        return this.fileGroupId;
    }
    /** 文件组id;uuid */
    public void setFileGroupId(String fileGroupId){
        this.fileGroupId = fileGroupId;
    }
    /** 旧文件名;上传时的文件名 */
    public String getNameOld(){
        return this.nameOld;
    }
    /** 旧文件名;上传时的文件名 */
    public void setNameOld(String nameOld){
        this.nameOld = nameOld;
    }
    /** 新文件名;重命名后的文件名 */
    public String getNameNew(){
        return this.nameNew;
    }
    /** 新文件名;重命名后的文件名 */
    public void setNameNew(String nameNew){
        this.nameNew = nameNew;
    }
    /** 文件格式 */
    public String getFormat(){
        return this.format;
    }
    /** 文件格式 */
    public void setFormat(String format){
        this.format = format;
    }
    /** 文件大小 */
    public Long getSize(){
        return this.size;
    }
    /** 文件大小 */
    public void setSize(Long size){
        this.size = size;
    }
    /** 实际路径 */
    public String getUri(){
        return this.uri;
    }
    /** 实际路径 */
    public void setUri(String uri){
        this.uri = uri;
    }
    /** 网络路径 */
    public String getUrl(){
        return this.url;
    }
    /** 网络路径 */
    public void setUrl(String url){
        this.url = url;
    }
    /** 创建人 */
    public Integer getCreateBy(){
        return this.createBy;
    }
    /** 创建人 */
    public void setCreateBy(Integer createBy){
        this.createBy = createBy;
    }
    /** 创建时间 */
    public Date getCreateTime(){
        return this.createTime;
    }
    /** 创建时间 */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
}