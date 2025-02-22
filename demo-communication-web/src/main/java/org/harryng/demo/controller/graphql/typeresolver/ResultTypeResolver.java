package org.harryng.demo.controller.graphql.typeresolver;

import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;
import org.apache.commons.lang3.StringUtils;
import org.harryng.demo.impl.asset.dto.AssetRes;
import org.harryng.demo.impl.organization.dto.OrganizationDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResultTypeResolver implements TypeResolver {
    private final Map<Class<?>, String> resultTypeMap = Map.of(
            AssetRes.class, "Asset",
            OrganizationDto.class, "Organization"
    );

    @Override
    public GraphQLObjectType getType(TypeResolutionEnvironment env) {
        final Object object = env.getObject();
//        if (object instanceof AssetRes) {
//            return env.getSchema().getObjectType("Asset");
//        } else if (object instanceof OrganizationDto) {
//            return env.getSchema().getObjectType("Organization");
//        }
        final String objectName = resultTypeMap.get(object.getClass());
        if (StringUtils.isNoneBlank(objectName)) {
            return env.getSchema().getObjectType(objectName);
        }
        return null;
    }
}
