package org.harryng.demo.controller.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.aop.GrpcHeaderServerInterceptor;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.controller.grpc.asset.*;
import org.harryng.demo.impl.asset.dto.AssetDto;
import org.harryng.demo.impl.asset.service.AssetService;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Optional;

import static org.harryng.demo.controller.grpc.asset.AssetControllerGrpc.*;


@Component
@RequiredArgsConstructor
@Slf4j
public class AssetGrpcController extends AssetControllerGrpc.AssetControllerImplBase {

    private final AssetService assetService;

    @Override
    public void findById(AssetIdReq request, StreamObserver<AssetResultRes> responseObserver) {
//        io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindByIdMethod(), responseObserver);
        final String token = GrpcHeaderServerInterceptor.HEADER_AUTHORIZATION.get();
        log.info("token:{}", token);
        try {
            final SessionHolder sessionHolder = SessionHolder.ANONYMOUS;
            final Optional<AssetDto> oAssetDto = assetService.getById(sessionHolder, request.getId(), new LinkedHashMap<>());
            final AssetResultRes res;
            if (oAssetDto.isPresent()) {
                final AssetDtoGrpc assetDtoGrpc = AssetDtoGrpc.newBuilder()
                        .setId(oAssetDto.get().getId())
                        .setName(oAssetDto.get().getName())
                        .setDescription(oAssetDto.get().getDescription())
                        .setOrgId(oAssetDto.get().getOrgId())
                        .setOrgTreepath(oAssetDto.get().getOrgTreepath())
                        .build();
                res = AssetResultRes.newBuilder().addAsset(assetDtoGrpc).build();
            } else {
                res = AssetResultRes.newBuilder().getDefaultInstanceForType();
            }
            responseObserver.onNext(res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            responseObserver.onError(e);
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void add(AssetReq request, StreamObserver<AssetRes> responseObserver) {
        io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    @Override
    public void edit(AssetReq request, StreamObserver<AssetRes> responseObserver) {
        io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditMethod(), responseObserver);
    }

    @Override
    public void remove(AssetIdReq request, StreamObserver<AssetRes> responseObserver) {
        io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveMethod(), responseObserver);
    }

    @Override
    public void findByName(AssetNameReq request, StreamObserver<AssetResultRes> responseObserver) {
        io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindByNameMethod(), responseObserver);
    }
}
