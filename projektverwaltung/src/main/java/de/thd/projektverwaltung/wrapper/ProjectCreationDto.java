
package de.thd.projektverwaltung.wrapper;

import de.thd.projektverwaltung.model.Projekt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public class ProjectCreationDto {
    private List<Projekt> projects;


    public void addProjekt(Projekt project) {
        this.projects.add(project);
    }


}




