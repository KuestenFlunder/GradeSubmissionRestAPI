package com.ltp.gradesubmission.exceptions;

public class EntityNotFoundException extends RuntimeException{

  public  EntityNotFoundException(Long entityId, Class<?> entity) {
      super(String.format("The %s with id %d could not be found",
              entity.getSimpleName().toLowerCase(),
              entityId));
  }

    public  EntityNotFoundException(String name, Class<?> entity) {
        super(String.format("The %s : %s could not be found",
                entity.getSimpleName().toLowerCase(),
                name));
    }

  public EntityNotFoundException(Long firstEntityId,Class<?> firstEntity,Long secondEntityId, Class<?> secondEntity){
      super(String.format("The grade with %s id: %d and %s id: %d does not exist in our records",
              firstEntity.getSimpleName().toLowerCase(),
              firstEntityId,
              secondEntity.getSimpleName().toLowerCase(),
              secondEntityId));
  }
}
