
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SystemConfigRepository;
import domain.SystemConfig;

@Service
@Transactional
public class SystemConfigService {

	@Autowired
	private SystemConfigRepository	systemConfigRepository;

	// Supporting Services ----------------------------------------------------

	@Autowired
	private AdminService			adminService;


	//CRUD Methods -------------------------

	public SystemConfig findOne(int systemConfigId) {
		Assert.isTrue(systemConfigId != 0);
		SystemConfig res = systemConfigRepository.findOne(systemConfigId);
		Assert.notNull(res);
		return res;
	}

	public SystemConfig get() {
		return systemConfigRepository.findAll().get(0);
	}

	public SystemConfig save(final SystemConfig systemConfig) {
		Assert.notNull(systemConfig);
		Assert.notNull(adminService.findByPrincipal());
		SystemConfig x =get();
		x.setTabooWordss(systemConfig.getTabooWordss());
		return systemConfigRepository.save(x);
	}

}
