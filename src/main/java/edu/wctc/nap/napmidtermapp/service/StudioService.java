package edu.wctc.nap.napmidtermapp.service;


import edu.wctc.nap.napmidtermapp.model.Animes;
import edu.wctc.nap.napmidtermapp.model.Studios;
import edu.wctc.nap.napmidtermapp.repository.AnimeRepository;
import edu.wctc.nap.napmidtermapp.repository.StudioRepository;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is a special Spring-enabled service class that delegates work to 
 * various Spring managed repository objects using special spring annotations
 * to perform dependency injection, and special annotations for transactions.
 * It also uses SLF4j to provide logging features.
 * 
 * Don't confuse the Spring @Respository annotation with the repository
 * classes (AuthorRepository, BookRespository). The annotation here is used 
 * solely to tell Spring to translate any exception messages into more
 * user friendly text. Any class annotated that way will do that.
 * 
 * @author jlombardo
 */
@Repository
@Transactional(readOnly = true)
public class StudioService {

    private transient final Logger LOG = LoggerFactory.getLogger(StudioService.class);

    @Inject
    private StudioRepository StudioRepo;

    @Inject
    private AnimeRepository AnimeRepo;

    public StudioService() {
    }

    public List<Studios> findAll() {
        return StudioRepo.findAll();
    }
   // public List<Animes> findByStudio(int id){
      //  List<Animes> animes = AnimeRepo.findAllByStudio(id);
     //   return animes;
    //}

    public Studios findById(String id) {
        return StudioRepo.findOne(new Integer(id));
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param author 
     */
    @Transactional
    public void remove(Studios author) {
        LOG.debug("Deleting author: " + author.getStudioName());
        StudioRepo.delete(author);
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param author 
     * @return  
     */
    @Transactional
    public Studios edit(Studios author) {
        return StudioRepo.saveAndFlush(author);
    }
}
