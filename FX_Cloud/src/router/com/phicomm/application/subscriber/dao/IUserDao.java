package com.phicomm.application.subscriber.dao;

import java.util.List;

import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.UserDto;
public interface IUserDao {
	public void add(User user);
	public void update(User user);
	public void delete(long id);
	public User load(long id);
	public List<User> list();
	public Pager<User> find();
	public Pager<User> search(String name,String value);
	public User loadByUsername(String username,String psd);
	public User loadByMac(String mac);
	public List<User> loadbyonlinemac(String onlinemac);
	public List<User> loadByUsernamePsd(String username,String psd);
	public void transOn();
	public User searchByMailPsdAct(String mail);
	public List<User> loadByMail(String mail);
	public User loadByMailPsdAct(String mail,String psd);
	public UserDto loadById(long id);
	public User loadByIdMail(long id,String mail);
}
