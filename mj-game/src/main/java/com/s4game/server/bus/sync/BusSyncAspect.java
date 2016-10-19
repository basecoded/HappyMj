package com.s4game.server.bus.sync;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.s4game.core.message.Message;
import com.s4game.core.sync.Lock;
import com.s4game.core.sync.LockManager;
import com.s4game.server.bus.init.command.InitCommands;
import com.s4game.server.bus.share.constants.BusShareConstant;
import com.s4game.server.bus.share.service.IRoleStateService;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年9月12日 上午10:36:25
 * 
 */
@Component
@Aspect
public class BusSyncAspect {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Resource
	private LockManager lockManager;

	@Resource
	private IRoleStateService roleStateService;

	@Pointcut(value = "execution(* com.s4game.server.bus..*Action.*(..) )")
	public void pointcut() {

	}

	@Around(value = "pointcut()")
	public void run(ProceedingJoinPoint joinpoint) throws Throwable {
		LOG.info(joinpoint.toShortString());

		Message message = (Message) joinpoint.getArgs()[0];
		String command = message.getCommand();
		String roleId = message.getRoleId();

		Lock lock = this.lockManager.getLock(BusShareConstant.COMPONENT_NAME, roleId);

		synchronized (lock) {
			
			if (this.roleStateService.isOnline(roleId) || InitCommands.ROLE_IN.equals(command)) {
				joinpoint.proceed();
				return;
			}
			
			LOG.error("role: {} bus not online. message: {}", roleId, message.toString());
		}
	}

	@AfterThrowing(value = "pointcut()", throwing = "e")
    public void exception(Exception e) {
        LOG.error("execute bus sync aspect error: {}", e.getMessage(), e);
    }

}
