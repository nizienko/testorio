package testorio.core;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by def on 13.01.17.
 */
public class FileWorker<C> {
    private static final Logger LOG = LoggerFactory.getLogger(FileWorker.class);

    private Class<C> typeParameterClass;
    private String fileName;
    public FileWorker(Class<C> typeParameterClass, String fileName) {
        this.typeParameterClass = typeParameterClass;
        this.fileName = fileName;
    }

    public void save(C data){
        try {
            JsonUtils.getMapper().writeValue(new File(fileName), data);
            LOG.info(String.format("Saved %s", fileName));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public C load(){
        try {
            return JsonUtils.getMapper().readValue(new File(fileName), typeParameterClass);
        } catch (IOException e) {
            LOG.warn(e.getMessage());
        }
        return null;
    }
}
