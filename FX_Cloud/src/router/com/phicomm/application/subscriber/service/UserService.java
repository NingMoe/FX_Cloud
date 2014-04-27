package com.phicomm.application.subscriber.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phicomm.application.subscriber.dao.IUserDao;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.UserDto;
import com.phicomm.application.subscriber.model.CloudException;

@Service("userService")
public class UserService implements IUserService {

	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserDto loadById(long id){
		return userDao.loadById(id);
	}
	@Override
	public void add(User user) {
		//User u = userDao.loadByUsername(user.getMAIL_ADDRESS(),user.getPASSWORD());
		//if(u!=null) throw new UserException("此邮箱已被注册！");
		userDao.add(user);
	}

	@Override
	public boolean checkMailDup(User user){
		User u = userDao.loadByUsername(user.getMailAddress(),user.getPassword());
		if(u!=null) throw new CloudException("此邮箱已被注册！");
		return true;
	}
	
	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public User load(long id) {
		return userDao.load(id);
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}
	
	@Override
	public User searchByMailPsdAct(String mail){
		return userDao.searchByMailPsdAct(mail);
	}
	
	@Override
	public List<User> loadbyonlinemac(String onlinemac) {
		return userDao.loadbyonlinemac(onlinemac);
	}
	
	@Override
	public List<User> loadByMail(String mail){
		return userDao.loadByMail(mail);
	}
	
	@Override
	public User loadByMailPsdAct(String mail,String psd){
		return userDao.loadByMailPsdAct(mail,psd);
	}
	@Override
	public Pager<User> find() {
		return userDao.find();
	}
	
	@Override
	public Pager<User> search(String name,String value) {
		return userDao.search( name, value);
	}

	@Override
	public User login(String Reger2Mail, String Reger2Psw) {
		User u = userDao.loadByUsername(Reger2Mail,Reger2Psw);
	//	if(u==null) throw new UserException("用户不存在");
		//if(!u.getReger2Psw().equals(Reger2Psw)) throw new UserException("�������");
		return u;
	}

	@Override
	public User loadByMailPsw(String Reger2Mail, String Reger2Psw) {
		User u = userDao.loadByUsername(Reger2Mail,Reger2Psw);
	//	if(u==null) throw new UserException("用户不存在");
		//if(!u.getReger2Psw().equals(Reger2Psw)) throw new UserException("�������");
		return u;
	}
	
	@Override
	public User loadbymac(String mac) {
		User u = userDao.loadByMac(mac);
	//	if(u==null) throw new UserException("用户不存在");
		//if(!u.getReger2Psw().equals(Reger2Psw)) throw new UserException("�������");
		return u;
	}
	
	public List<User> loadByUsernamePsd(String username,String psd){
		return userDao.loadByUsernamePsd(username, psd);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void transOn(User user){
		try{
		userDao.add(user);
		userDao.update(user);
		}catch(Exception ex){
			System.out.println("test rollback");
			ex.getStackTrace();
			System.out.println(ex);
		}
	}

	@Override
	public User loadByIdMail(long id, String mail) {
		return userDao.loadByIdMail(id, mail);
	}


}
