package pojo;

public class RequestPojo {
	private int reqId;
	private int workerId;
	private double amount;
	private String description;
	private String status;
	private String resolvedBy;

	public RequestPojo() {
		super();
	}
	public RequestPojo(int reqID, int workerId, double amount, String description, String status, String resolvedBy) {
		super();
		this.reqId = reqID;
		this.workerId = workerId;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.resolvedBy = resolvedBy;

	}
	
	public RequestPojo(int workerId, double amount, String description, String status, String resolvedBy) {
		super();
		this.workerId = workerId;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.resolvedBy = resolvedBy;
	}

	public RequestPojo(int workerId, String status, String resolvedBy) {
		
		this.workerId = workerId;
		this.status = status;
		this.resolvedBy = resolvedBy;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqID) {
		this.reqId = reqID;
	}

	public int getworkerId() {
		return workerId;
	}

	public void setworkerId(int workerId) {
		this.workerId = workerId;
	}

	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResolvedBy() {
		return resolvedBy;
	}

	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestPojo other = (RequestPojo) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (workerId != other.workerId)
			return false;
		if (reqId != other.reqId)
			return false;
		if (resolvedBy == null) {
			if (other.resolvedBy != null)
				return false;
		} else if (!resolvedBy.equals(other.resolvedBy))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [reqId=" + reqId + ", workerId=" + workerId + ", amount=" + amount + ", description=" + description
				+ ", status=" + status + ", resolvedBy=" + resolvedBy + "]";
	}

}
	