package br.com.store.beans;

import br.com.store.models.User;
import br.com.store.repositories.UserRepository;
import br.com.store.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "portal")
@SessionScoped
public class PortalBean implements Serializable {

    private final UserRepository userRepository;
    
    private User user;

    public User getUser() {
        return user;
    }
    
    public List<User> getAllUsers(){
        return userRepository.getAll();
    }
    
    public PortalBean() {
        userRepository = new UserRepository(JPAUtil.getEntityManager());
    }
    
    public String register(){
        user = new User();
        return "register";
    }
    
        
    public String list(){
        return "index";
    }
    
    
    public String confirm(){
        if (userRepository.create(user)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário registrado com sucesso!"));
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar usuário.", "Verifique os dados informados e tente novamente"));
            return "register";
        }
    }
    
}
