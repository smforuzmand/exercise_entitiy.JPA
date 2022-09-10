package se.lexicom.exercise_entitiy_jpa.dao;


import se.lexicom.exercise_entitiy_jpa.model.AppUser;

public interface AppUserDAO {
    public AppUser save(AppUser appUser);

    public AppUser findById(int id);

    public void delete(AppUser appUser);





}
