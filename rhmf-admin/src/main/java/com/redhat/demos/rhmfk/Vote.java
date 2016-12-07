package com.redhat.demos.rhmfk;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.Enumerated;
import com.redhat.demos.rhmfk.VoteSelection;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Vote implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String deviceId;

	@Column
	private String pollName;

	@Enumerated
	private VoteSelection voteSelection;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vote)) {
			return false;
		}
		Vote other = (Vote) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPollName() {
		return pollName;
	}

	public void setPollName(String pollName) {
		this.pollName = pollName;
	}

	public VoteSelection getVoteSelection() {
		return voteSelection;
	}

	public void setVoteSelection(VoteSelection voteSelection) {
		this.voteSelection = voteSelection;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (deviceId != null && !deviceId.trim().isEmpty())
			result += "deviceId: " + deviceId;
		if (pollName != null && !pollName.trim().isEmpty())
			result += ", pollName: " + pollName;
		return result;
	}
}