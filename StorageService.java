package edu.name.schedule.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.name.schedule.model.Schedule;
import java.io.File;
import java.io.IOException;

public class StorageService {
    private final ObjectMapper mapper = new ObjectMapper();
    public void save(File file, Schedule schedule) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, schedule);
    }
    public Schedule load(File file) throws IOException {
        return mapper.readValue(file, Schedule.class);
    }
}
