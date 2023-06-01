package com.stackroute.PaymentService.dto;

public class OrderPublisher {

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

	public OrderPublisher(Integer orderPublisherId, OrderDTO orderDTO, BuyerDTO buyerDTO) {
		super();
		this.orderPublisherId = orderPublisherId;
		this.orderDTO = orderDTO;
		this.buyerDTO = buyerDTO;
	}

	public OrderPublisher() {
		super();
		// TODO Auto-generated constructor stub
	}
}
