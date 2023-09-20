package com.example.springmodels.repos;

import com.example.springmodels.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.naming.directory.SearchResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class UniversalRepositoryImpl implements UniversalRepository{
    private final ConcertRepository concertRepository;
    private final ConcertSongsRepository concertSongsRepository;
    private final CourseDurationRepository courseDurationRepository;
    private final CoursePaymentRepository coursePaymentRepository;
    private final CoursePaymentTypeRepository coursePaymentTypeRepository;
    private final CourseRepository courseRepository;
    private final CourseTypeRepository courseTypeRepository;
    private final StudentSongRepository studentSongRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UniversalRepositoryImpl(ConcertRepository concertRepository,
                                   ConcertSongsRepository concertSongsRepository,
                                   CourseDurationRepository courseDurationRepository,
                                   CoursePaymentRepository coursePaymentRepository,
                                   CoursePaymentTypeRepository coursePaymentTypeRepository,
                                   CourseRepository courseRepository,
                                   CourseTypeRepository courseTypeRepository,
                                   StudentSongRepository studentSongRepository,
                                   Map<String, org.springframework.data.repository.Repository<?, ?>> repositoryMap ) {
        this.concertRepository = concertRepository;
        this.concertSongsRepository = concertSongsRepository;
        this.courseDurationRepository = courseDurationRepository;
        this.coursePaymentRepository = coursePaymentRepository;
        this.coursePaymentTypeRepository = coursePaymentTypeRepository;
        this.courseRepository = courseRepository;
        this.courseTypeRepository = courseTypeRepository;
        this.studentSongRepository = studentSongRepository;
        this.repositoryMap = repositoryMap;
    }
    private final Map<String, org.springframework.data.repository.Repository<?, ?>> repositoryMap;
    @Override
    public Optional<?> findEntityById(String modelName, Long id) {
        return switch (modelName) {
            case "Concert" -> concertRepository.findById(id);
            case "ConcertSongs" -> concertSongsRepository.findById(id);
            case "Course" -> courseRepository.findById(id);
            case "CourseDuration" -> courseDurationRepository.findById(id);
            case "CoursePayment" -> coursePaymentRepository.findById(id);
            case "CoursePaymentType" -> coursePaymentTypeRepository.findById(id);
            case "CourseType" -> courseTypeRepository.findById(id);
            case "StudentSong" -> studentSongRepository.findById(id);
            default -> Optional.empty();
        };
    }

    @Override
    public void saveEntity(String modelName, Object entity) {
        switch (modelName) {
            case "Concert" -> concertRepository.save((Concert) entity);
            case "ConcertSongs" -> concertSongsRepository.save((ConcertSongs) entity);
            case "Course" -> courseRepository.save((Course) entity);
            case "CourseDuration" -> courseDurationRepository.save((CourseDuration) entity);
            case "CoursePayment" -> coursePaymentRepository.save((CoursePayment) entity);
            case "CoursePaymentType" -> coursePaymentTypeRepository.save((CoursePaymentType) entity);
            case "CourseType" -> courseTypeRepository.save((CourseType) entity);
            case "StudentSong" -> studentSongRepository.save((StudentSong) entity);
            default -> throw new IllegalArgumentException("Invalid model name");
        }
    }

    @Override
    public <T> void updateEntity(T entity, Map<String, String> fieldValues) {
        Class<?> entityClass = entity.getClass();

        for (Field field : entityClass.getDeclaredFields()) {
            String fieldName = field.getName();

            if (fieldValues.containsKey(fieldName)) {
                String fieldValue = fieldValues.get(fieldName);

                try {
                    String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class<?> fieldClass = field.getType();
                    Class<?>[] paramTypes = new Class<?>[] { fieldClass };
                    java.lang.reflect.Method setterMethod = entityClass.getMethod(setterMethodName, paramTypes);

                    Object convertedValue = convertStringToType(fieldClass, fieldValue);

                    setterMethod.invoke(entity, convertedValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<SearchResult> searchEntities(String modelName, String fieldName, String fieldValue) throws ClassNotFoundException {
        String jpql = "SELECT e FROM " + modelName + " e WHERE e." + fieldName + " = :fieldValue";
        Query query = entityManager.createQuery(jpql, Class.forName("com.example.modelsspring.models." + modelName));
        query.setParameter("fieldValue", fieldValue);
        return query.getResultList();
    }
    private Class<?> getEntityClassByName(String modelName) {
        try {
            return Class.forName("com.example.modelsspring.models." + modelName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid model name: " + modelName);
        }
    }
    @Override
    public void deleteEntityById(String modelName, Long id) {
        CrudRepository<?, Long> repository = (CrudRepository<?, Long>) repositoryMap.get(modelName);

        if (repository != null) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid model name");
        }

        switch (modelName) {
            case "Concert" -> concertRepository.deleteById(id);
            case "ConcertSongs" -> concertSongsRepository.deleteById(id);
            case "Course" -> courseRepository.deleteById(id);
            case "CourseDuration" -> courseDurationRepository.deleteById(id);
            case "CoursePayment" -> coursePaymentRepository.deleteById(id);
            case "CoursePaymentType" -> coursePaymentTypeRepository.deleteById(id);
            case "CourseType" -> courseTypeRepository.deleteById(id);
            case "StudentSong" -> studentSongRepository.deleteById(id);
            default -> throw new IllegalArgumentException("Invalid model name");
        }
    }

    private Object convertStringToType(Class<?> targetType, String value) {
        if (targetType == String.class) {
            return value;
        } else if (targetType == Integer.class || targetType == int.class) {
            return Integer.parseInt(value);
        } else if (targetType == Long.class || targetType == long.class) {
            return Long.parseLong(value);
        }
        return null;
    }
}
