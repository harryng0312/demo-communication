package org.harryng.demo.impl.base.mapper;

import lombok.NonNull;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//@Mapper(config = DtoEntityMapperConfig.class)
public interface BaseMapper <Ent, Dget, Dadd, Dedit> {
    Dadd convertEntToAddDto(@NonNull Ent source);
    Dedit convertEntToEditEto(@NonNull Ent source);
    Dget convertEntToGetDto(@NonNull Ent source);

    Dget convertAddToGetDto(@NonNull Dadd source);
    Dget convettEditToGetDto(@NonNull Dedit source);

    Ent convertAddDtoToEnt(@NonNull Dadd dadd);
    Ent convertEditDtoToEnt(@NonNull Dedit dedit);


}
