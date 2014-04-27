package com.phicomm.application.subscriber.service;

import java.util.List;

import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.UserDto;

public interface IUserService {
	public void add(User user);
	public boolean checkMailDup(User user);
	public void update(User user);
	public void delete(long id);
	public User load(long id);
	public User loadbymac(String mac);
	public List<User> list();
	public Pager<User> find();
	public Pager<User> search(String name,String value);
	public User login(String username,String password);
	public List<User> loadbyonlinemac(String onlinemac);
	public List<User> loadByUsernamePsd(String username,String psd);
	public User loadByMailPsw(String Reger2Mail, String Reger2Psw);
	public void transOn(User user);
	public User searchByMailPsdAct(String mail);
	public List<User> loadByMail(String mail);
	public User loadByMailPsdAct(String mail,String psd);
	public UserDto loadById(long id);
	public User loadByIdMail(long id,String mail);
}
