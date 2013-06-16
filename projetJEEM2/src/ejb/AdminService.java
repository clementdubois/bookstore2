package ejb;

import entities.Admin;

public interface AdminService extends GenericCRUDService<Admin>{
  public Admin login(String login, String password);
}