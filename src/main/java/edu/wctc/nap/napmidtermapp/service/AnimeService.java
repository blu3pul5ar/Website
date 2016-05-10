package edu.wctc.nap.napmidtermapp.service;

import edu.wctc.nap.napmidtermapp.model.Animes;
import edu.wctc.nap.napmidtermapp.repository.AnimeRepository;
import edu.wctc.nap.napmidtermapp.repository.StudioRepository;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AnimeService {
    private transient final Logger LOG = LoggerFactory.getLogger(AnimeService.class);
    
    @Inject
    private AnimeRepository AnimeRepo;
    
    @Inject
    private StudioRepository StudioRepo;

    public AnimeService() {
    }
    
    public List<Animes> findAll() {
        return AnimeRepo.findAll();
    }
    
    public Animes findById(String id) {
        return AnimeRepo.findOne(new Integer(id));
    }
    
    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param book 
     */
    @Transactional
    public void remove(Animes anime) {
        LOG.debug("Deleting book: " + anime.getProductName());
        AnimeRepo.delete(anime);
    }
    
    public List<Animes> findAllByStudio(int id){
        return AnimeRepo.findAllByStudio(id);
    }
    
    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param book 
     * @return  
     */
    @Transactional
    public Animes edit(Animes book) {
        return AnimeRepo.saveAndFlush(book);
    }
    
}
