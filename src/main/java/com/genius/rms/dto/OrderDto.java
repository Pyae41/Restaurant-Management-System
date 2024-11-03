package com.genius.rms.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    public long userId;
    public List<Long> menuIds;
}
