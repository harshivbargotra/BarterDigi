package com.stackroute.UserService.dto;

import org.springframework.data.annotation.Id;

import com.stackroute.UserService.entity.BankDetails;

public class OrderPublisher {

	@Id
    private Integer orderPublisherId;
    private OrderDTO orderDTO;

    public Integer getOrderPublisherId() {
        return orderPublisherId;
    }

    public void setOrderPublisherId(Integer orderPublisherId) {
        this.orderPublisherId = orderPublisherId;
    }

    private BuyerDTO buyerDTO;

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public BuyerDTO getBuyerDTO() {
        return buyerDTO;
    }

    public void setBuyerDTO(BuyerDTO buyerDTO) {
        this.buyerDTO = buyerDTO;
    }

	public OrderPublisher() {
		super();
	}
    
}
