/**
 * Copyright (C) 2014 Seagate Technology.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.seagate.kinetic.monitor.internal.service;

import java.util.ArrayList;
import java.util.List;

import kinetic.admin.Capacity;
import kinetic.admin.Statistics;
import kinetic.admin.Temperature;
import kinetic.admin.Utilization;

import com.google.gson.Gson;

/**
 * 
 * Heart beat prototype message.
 * 
 */
public class NodeInfo {

	// message source host name/ip
	private String host = "localhost";

	// message source port
	private int port = 8123;

	// message source tls port
	private int tlsPort = 8443;

	private int status = 0;

	private long unavailableTimeInSeconds = 0;

	private List<Temperature> temperatures = new ArrayList<Temperature>();

	private List<Utilization> utilizations = new ArrayList<Utilization>();

	private List<Statistics> statistics = new ArrayList<Statistics>();

	private Capacity capacity = null;

	private static Gson gson = new Gson();

	public long getUnavailableTimeInSeconds() {
		return unavailableTimeInSeconds;
	}

	public void setUnavailableTimeInSeconds(long unavailableTimeInSeconds) {
		this.unavailableTimeInSeconds = unavailableTimeInSeconds;
		if (unavailableTimeInSeconds >= 0) {
			this.status = 0;
		} else {
			this.status = 1;
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTlsPort() {
		return tlsPort;
	}

	public void setTlsPort(int tlsPort) {
		this.tlsPort = tlsPort;
	}

	public Capacity getCapacity() {
		return capacity;
	}

	public void setCapacity(Capacity capacity) {
		this.capacity = capacity;
	}

	public List<Temperature> getTemperatures() {
		return temperatures;
	}

	public List<Utilization> getUtilizations() {
		return utilizations;
	}

	public List<Statistics> getStatistics() {
		return statistics;
	}

	public void setTemperatures(List<Temperature> temperatures) {
		this.temperatures = temperatures;
	}

	public void setUtilizations(List<Utilization> utilizations) {
		this.utilizations = utilizations;
	}

	public void setStatistics(List<Statistics> statistics) {
		this.statistics = statistics;
	}

	public static String toJson(Object obj) {
		return gson.toJson(obj, NodeInfo.class);
	}

	public static NodeInfo fromJson(String str) {
		return gson.fromJson(str, NodeInfo.class);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
