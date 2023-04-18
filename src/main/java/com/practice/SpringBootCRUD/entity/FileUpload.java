package com.practice.SpringBootCRUD.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileUpload extends BaseModel{
    private  String filename;
    private  String fileUri;
}
