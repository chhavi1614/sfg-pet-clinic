package sfgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sfgpetclinic.model.Speciality;
import sfgpetclinic.model.Vet;
import sfgpetclinic.services.VetService;

import java.util.Set;

@Service
@Profile({"default","map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityMapService specialityMapService;

    public VetServiceMap(SpecialityMapService specialityMapService) {
        this.specialityMapService = specialityMapService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        if(object!=null) {
            if(object.getSpecialities()!=null) {
                object.getSpecialities().forEach( speciality->{
                    if(speciality.getId() == null){
                        Speciality savedSpeciality = specialityMapService.save(speciality);
                        speciality.setId(savedSpeciality.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
