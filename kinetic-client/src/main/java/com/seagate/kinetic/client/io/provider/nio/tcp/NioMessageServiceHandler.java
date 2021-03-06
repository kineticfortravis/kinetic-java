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
package com.seagate.kinetic.client.io.provider.nio.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.seagate.kinetic.client.io.provider.spi.ClientMessageService;
import com.seagate.kinetic.common.lib.KineticMessage;

/**
 *
 * @author chiaming
 *
 */
public class NioMessageServiceHandler extends
		SimpleChannelInboundHandler<KineticMessage> {

	private static final Logger logger = Logger
			.getLogger(NioMessageServiceHandler.class.getName());

	private ClientMessageService mservice = null;

	public NioMessageServiceHandler(ClientMessageService mservice) {
		this.mservice = mservice;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			KineticMessage message)
			throws Exception {

		this.mservice.routeMessage(message);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {

		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				cause);

		this.mservice.close();

		ctx.close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// close my message service handler
		this.mservice.close();

		// close connection
		ctx.close();
	}

}
