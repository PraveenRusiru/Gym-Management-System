package org.example.fitness.model;

import org.example.fitness.dto.ExpiringMemberDto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpiringMemberModel {

    public ArrayList<ExpiringMemberDto> getExpiringMembers() {
        ArrayList<ExpiringMemberDto> al=new ArrayList<>();

        ExpiringMemberDto dto1=new ExpiringMemberDto("M001","Praveen Rusiru","09-09-2003","Elite");
        al.add(dto1);

        ExpiringMemberDto dto2=new ExpiringMemberDto("M002","Ranil Wikramasinge","09-10-2003","Elite");
        al.add(dto2);

        ExpiringMemberDto dto3=new ExpiringMemberDto("M003","Dilith Jayawardana","09-09-2003","Elite");
        al.add(dto3);

        ExpiringMemberDto dto4=new ExpiringMemberDto("M004","Ashu Marasinghe","09-09-2003","Premium");
        al.add(dto4);

        ExpiringMemberDto dto5=new ExpiringMemberDto("M005","Tilwin Silva","09-09-2003","Elite");
        al.add(dto5);

        ExpiringMemberDto dto6=new ExpiringMemberDto("M006","Rathna Gamage","09-09-2003","Elite");
        al.add(dto6);
    return al;

    }


}
