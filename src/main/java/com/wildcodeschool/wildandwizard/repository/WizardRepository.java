package com.wildcodeschool.wildandwizard.repository;

import com.wildcodeschool.wildandwizard.entity.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class WizardRepository implements WizardDao {

    /* Autowired wird nicht benötigt, weil das @Repository dies schon übernimmt!
       und @Qualifier wird hier auch nicht benötigt, da es nur ein Repository gibt.
     */
    // @Autowired
    // @Qualifier("wizard")

    private static List<Wizard> wizards = new ArrayList<>(
            Arrays.asList(
                    new Wizard(1L, "Harry", "Potter", new Date(80, 6, 31), "London", "", false)
            )
    );

    @Override
    public Wizard save(Wizard wizard) {

        wizard.setId((long) (wizards.size() + 1));
        wizards.add(wizard);
        return wizard;
    }

    @Override
    public Wizard findById(Long id) {

        for (Wizard wizard : wizards) {
            if (wizard.getId().equals(id)) {
                return wizard;
            }
        }
        return null;
    }

    @Override
    public List<Wizard> findAll() {

        return wizards;
    }

    @Override
    public Wizard update(Wizard wizard) {

        for (Wizard update : wizards) {
            if (update.getId().equals(wizard.getId())) {
                update.setFirstName(wizard.getFirstName());
                update.setLastName(wizard.getLastName());
                update.setBirthday(wizard.getBirthday());
                update.setBirthPlace(wizard.getBirthPlace());
                update.setBiography(wizard.getBiography());
                update.setMuggle(wizard.isMuggle());

                return update;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {

        for (Wizard wizard : wizards) {
            if (wizard.getId().equals(id)) {
                wizards.remove(wizard);
                break;
            }
        }
    }
}
