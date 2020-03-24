/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.1.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.itechart.container.spring.users.generated.api;

import java.util.UUID;
import com.itechart.container.spring.users.generated.model.UpdateUserRequest;
import com.itechart.container.spring.users.generated.model.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-03-24T13:41:48.118567+03:00[Europe/Minsk]")

@Validated
@Api(value = "users", description = "the users API")
public interface UsersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "deleteUser", notes = "User can delete only his account, admin can delete all accounts, requires log in", authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 400, message = "Invalid UUID"),
        @ApiResponse(code = 403, message = "User trying to delete another user"),
        @ApiResponse(code = 404, message = "User already deleted or doesn't exist") })
    @RequestMapping(value = "/users/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteUser(@ApiParam(value = "",required=true) @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getCurrentUser", notes = "Requires log in", response = User.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class),
        @ApiResponse(code = 404, message = "User already deleted or doesn't exist") })
    @RequestMapping(value = "/users/current",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<User> getCurrentUser() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\",  \"password\" : \"password\",  \"deletedAt\" : \"2000-01-23T04:56:07.000+00:00\",  \"phone\" : \"+753 92 321 54 76\",  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",  \"email\" : \"email\",  \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getUser", notes = "Requires log in", response = User.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class),
        @ApiResponse(code = 400, message = "Invalid UUID"),
        @ApiResponse(code = 404, message = "User already deleted or doesn't exist") })
    @RequestMapping(value = "/users/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<User> getUser(@ApiParam(value = "",required=true) @PathVariable("id") UUID id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\",  \"password\" : \"password\",  \"deletedAt\" : \"2000-01-23T04:56:07.000+00:00\",  \"phone\" : \"+753 92 321 54 76\",  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",  \"email\" : \"email\",  \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getUsers", notes = "", response = User.class, responseContainer = "List", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "User trying to access admin's functionality") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<User>> getUsers(@Min(1)@ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page,@Min(1)@ApiParam(value = "", defaultValue = "100") @Valid @RequestParam(value = "size", required = false, defaultValue="100") Integer size) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\",  \"password\" : \"password\",  \"deletedAt\" : \"2000-01-23T04:56:07.000+00:00\",  \"phone\" : \"+753 92 321 54 76\",  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",  \"email\" : \"email\",  \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "updateUser", notes = "Requires log in", response = User.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class),
        @ApiResponse(code = 400, message = "Invalid phone"),
        @ApiResponse(code = 404, message = "User deleted or doesn't exist") })
    @RequestMapping(value = "/users/{id}/profile",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<User> updateUser(@ApiParam(value = "",required=true) @PathVariable("id") UUID id,@ApiParam(value = "" ,required=true )  @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\",  \"password\" : \"password\",  \"deletedAt\" : \"2000-01-23T04:56:07.000+00:00\",  \"phone\" : \"+753 92 321 54 76\",  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",  \"email\" : \"email\",  \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
