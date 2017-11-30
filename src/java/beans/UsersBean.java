package beans;

import entities.Devices;
import entities.Users;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
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
public class UsersBean implements Serializable {
	
	@PersistenceContext
	private EntityManager em;
	@Resource
	UserTransaction utx;
	
	public List<Users> getUsers() {
		return em.createQuery("select u from Users u", Users.class)
				.getResultList();
	}
	
	public void deleteUsers(Long uID) {
		if (uID == null) {
			return;
		}
		try {
			utx.begin();
			Users u = em.find(Users.class, uID);
			if(u != null) {
				em.remove(u);
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
	
	public void createUsers(UsersEditBean newU){
		if (newU == null || newU.getId() == null) {     
			return;
		}
		try {
			utx.begin();
			Users u = new Users();
			u.setId(newU.getId());
			u.setLogin(newU.getLogin());
			if (em.createQuery("select u.login from Users u", Users.class)
					.getResultList().contains(newU.getLogin()))
				return;
			u.setPassword(newU.getPassword());
			u.setMoney(newU.getMoney());
			if(u != null) {
				em.persist(u);
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