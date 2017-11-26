package beans;

import entities.Devices;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author Александр
 */
@ManagedBean
@ViewScoped
public class DevicesBean implements Serializable {
	
	@PersistenceContext
	private EntityManager em;
	@Resource
	UserTransaction utx;
	
	public List<Devices> getDevices() {
		return em.createQuery("select d from Devices d", Devices.class)
				.getResultList();
	}
	
	public void deleteDevices(Long dID) {
		if (dID == null) {
			return;
		}
		try {
			utx.begin();
			Devices d = em.find(Devices.class, dID);
			if(d != null) {
				em.remove(d);
			}
			utx.commit();
		} catch (Exception ex) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"DB Error:", ex.getLocalizedMessage()));
			ex.printStackTrace(System.err);
			try {
				utx.rollback();
			} catch (Exception exc) {
				exc.printStackTrace(System.err);
				ctx.addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"DB Error:", exc.getLocalizedMessage()));
			}
		}
	}
	
	public void createDevices(DevicesEditBean newD){
		if (newD == null || newD.getId() == null) {     
			return;
		}
		try {
			utx.begin();
			Devices d = new Devices();
			d.setId(newD.getId());
			d.setPhoneNum(newD.getPhoneNum());
			d.setUserId(newD.getUserId());
			if(d != null) {
				em.persist(d);
			}
			utx.commit();
		} catch (Exception ex) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"DB Error:", ex.getLocalizedMessage()));
			ex.printStackTrace(System.err);
			try {
				utx.rollback();
			} catch (Exception exc) {
				exc.printStackTrace(System.err);
			}
		}
	}
}