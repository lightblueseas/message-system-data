
    create table BaseAttributesEntity (
        id int4 not null,
        primary key (id)
    );
create table addresses (
        id int4 not null,
        address_comment varchar(100),
        geohash varchar(16),
        latitude varchar(12),
        longitude varchar(12),
        street varchar(64),
        streetnumber varchar(5),
        federalstate_id int4,
        zipcode_id int4,
        primary key (id)
    );
create table attributes (
        id int4 not null,
        name varchar(64),
        type varchar(256),
        value varchar(2048),
        primary key (id)
    );
create table base_attributes (
        base_attributes_id int4 not null,
        attributes_id int4 not null,
        primary key (base_attributes_id, attributes_id)
    );
create table blacklisted_contacts (
        user_data_id int4 not null,
        blacklisted_id int4 not null,
        primary key (user_data_id, blacklisted_id)
    );
create table contactmethods (
        id int4 not null,
        contactmethod varchar(255),
        contactvalue varchar(1024),
        primary key (id)
    );
create table countries (
        id int4 not null,
        iso3166_a2name varchar(2),
        iso3166_a3name varchar(3),
        iso3166_number varchar(3),
        name varchar(128),
        primary key (id)
    );
create table federalstates (
        id int4 not null,
        iso3166_a2code varchar(6),
        name varchar(128),
        subdivision_category varchar(128),
        subdivision_name varchar(56),
        country_id int4,
        primary key (id)
    );
create table message_attachments (
        message_id int4 not null,
        resource_id int4 not null,
        primary key (message_id, resource_id)
    );
create table message_recipients (
        id int4 not null,
        message_id int4,
        recipient_id int4,
        recipient_email int4,
        primary key (id)
    );
create table messages (
        id int4 not null,
        failed2sentemail bool,
        folder varchar(64),
        messageContent varchar(21845),
        messagetype varchar(255),
        read_flag bool,
        recipient_deleted_flag bool,
        sender_deleted_flag bool,
        sent_date timestamp,
        spam_flag bool,
        state varchar(255),
        subject varchar(1000),
        parent int4,
        sender int4,
        sender_email int4,
        primary key (id)
    );
create table permissions (
        id int4 not null,
        description varchar(64),
        permissionName varchar(64) unique,
        shortcut varchar(10) unique,
        primary key (id)
    );
create table recommendations (
        id int4 not null,
        email varchar(1024),
        invitation_text varchar(1024),
        sent bool,
        recommended_id int4,
        user_id int4,
        primary key (id)
    );
create table relation_permissions (
        id int4 not null,
        provider_id int4,
        subscriber_id int4,
        primary key (id)
    );
create table reset_passwords (
        id int4 not null,
        expiry_date timestamp,
        generated_password varchar(1024),
        starttime timestamp,
        user_id int4,
        primary key (id)
    );
create table resources (
        id int4 not null,
        checksum varchar(255),
        content BYTEA,
        contentType varchar(64),
        created timestamp,
        deleted_flag bool,
        description varchar(1024),
        filename varchar(1024),
        filesize varchar(64),
        primary key (id)
    );
create table robinsons (
        id int4 not null,
        robinson_user_id int4,
        primary key (id)
    );
create table role_permissions (
        role_id int4 not null,
        permission_id int4 not null,
        primary key (role_id, permission_id)
    );
create table roles (
        id int4 not null,
        description varchar(64),
        rolename varchar(64) unique,
        primary key (id)
    );
create table rule_violations (
        id int4 not null,
        description varchar(1000),
        reason varchar(255),
        detector_user_id int4,
        violator_user_id int4,
        primary key (id)
    );
create table user_addresses (
        user_data_id int4 not null,
        addresses_id int4 not null,
        primary key (user_data_id, addresses_id)
    );
create table user_contactmethods (
        user_data_id int4 not null,
        contactmethods_id int4 not null,
        primary key (user_data_id, contactmethods_id)
    );
create table user_contacts (
        user_data_id int4 not null,
        user_contact_id int4 not null,
        primary key (user_data_id, user_contact_id)
    );
create table user_data (
        id int4 not null,
        birthname varchar(64),
        dateofbirth timestamp,
        firstname varchar(64),
        gender varchar(255),
        ip_address varchar(16),
        lastname varchar(64),
        locale varchar(12),
        primary_address_id int4,
        primary key (id)
    );
create table user_relation_permissions (
        user_relation_permission_id int4 not null,
        permission_id int4 not null,
        primary key (user_relation_permission_id, permission_id)
    );
create table user_resources (
        user_data_id int4 not null,
        resources_id int4 not null,
        primary key (user_data_id, resources_id)
    );
create table user_roles (
        user_id int4 not null,
        role_id int4 not null,
        primary key (user_id, role_id)
    );
create table users (
        id int4 not null,
        active bool,
        locked bool,
        pw varchar(1024),
        salt varchar(8),
        username varchar(256) unique,
        user_data int4,
        primary key (id)
    );
create table zipcodes (
        id int4 not null,
        city varchar(128),
        zipcode varchar(10) not null,
        country_id int4,
        primary key (id)
    );


create type messageStateType as enum (
	'SIGNED', 'CONTACTED', 'EXPELLED', 'UNREPLIED', 'DELETED'
);
create type messageTypeType as enum (
	'MAIL', 'REPLY', 'NOTE'
);

create type contactmethodType as enum (
	'EMAIL', 'MAIL', 'TELEFON', 'FAX', 'MOBILE', 'SMS', 'MESSENGER', 'INTERNET', 'NEWSGROUP'
);
create type genderType as enum (
	'MALE', 'FEMALE', 'UNDEFINED'
);
alter table messages drop column state;    
alter table messages add state messageStateType;

alter table messages drop column messagetype;    
alter table messages add messagetype messageTypeType;

alter table contactmethods drop column contactmethod;    
alter table contactmethods add contactmethod contactmethodType;

alter table user_data drop column gender;    
alter table user_data add gender genderType;
create index IDX_FEDERALSTATE_ID on addresses (federalstate_id);
create index IDX_ZIPCODE_ID on addresses (zipcode_id);
alter table addresses add constraint FK_ADDRESSES_FEDERALSTATE_ID foreign key (federalstate_id) references federalstates;
alter table addresses add constraint FK_ADDRESSES_ZIPCODE_ID foreign key (zipcode_id) references zipcodes;
alter table base_attributes add constraint FK82063B85D4CCA38D foreign key (base_attributes_id) references BaseAttributesEntity;
alter table base_attributes add constraint FK82063B8520E98507 foreign key (attributes_id) references attributes;
alter table blacklisted_contacts add constraint FKA1253AB66B955CF9 foreign key (blacklisted_id) references users;
alter table blacklisted_contacts add constraint FKA1253AB6A09C615E foreign key (user_data_id) references user_data;
create index IDX_COUNTRY_ID on federalstates (country_id);
alter table federalstates add constraint FK_FEDERAL_STATES_COUNTRY_ID foreign key (country_id) references countries;
alter table message_attachments add constraint FK64E05778442255DC foreign key (message_id) references messages;
alter table message_attachments add constraint FK64E05778E812C08D foreign key (resource_id) references resources;
create index IDX_RECIPIENT_ID on message_recipients (recipient_id);
create index IDX_MESSAGE_RECIPIENTS_ID on message_recipients (message_id);
create index IDX_RECIPIENT_EMAIL on message_recipients (recipient_email);
alter table message_recipients add constraint FK_MESSAGE_RECIPIENTS_RECIPIENT_EMAIL foreign key (recipient_email) references contactmethods;
alter table message_recipients add constraint FK_MESSAGE_RECIPIENTS_RECIPIENT_ID foreign key (recipient_id) references users;
alter table message_recipients add constraint FK_MESSAGE_RECIPIENTS_MESSAGE_ID foreign key (message_id) references messages;
create index IDX_PARENT_MESSAGE_ID on messages (parent);
create index IDX_SENDER on messages (sender);
create index IDX_SENDER_EMAIL on messages (sender_email);
alter table messages add constraint FK_MESSAGES_SENDER foreign key (sender) references users;
alter table messages add constraint FK_MESSAGES_SENDER_EMAIL foreign key (sender_email) references contactmethods;
alter table messages add constraint FK_PARENT_MESSAGE_ID foreign key (parent) references messages;
create index IDX_RECOMMENDATIONS_USER_ID on recommendations (user_id);
create index IDX_RECOMMENDATIONS_RECOMMENDED_ID on recommendations (recommended_id);
alter table recommendations add constraint FK_RECOMMENDATIONS_USER_ID foreign key (user_id) references users;
alter table recommendations add constraint FK_RECOMMENDATIONS_RECOMMENDED_ID foreign key (recommended_id) references users;
create index IDX_PROVIDER_ID on relation_permissions (provider_id);
create index IDX_SUBSCRIBER_ID on relation_permissions (subscriber_id);
alter table relation_permissions add constraint FK_USER_RELATION_PERMISSIONS_PROVIDER_ID foreign key (provider_id) references users;
alter table relation_permissions add constraint FK_USER_RELATION_PERMISSIONS_SUBSCRIBER_ID foreign key (subscriber_id) references users;
create index IDX_RESET_PASSWORDS_USER_ID on reset_passwords (user_id);
alter table reset_passwords add constraint FK_RESET_PASSWORDS_USER_ID foreign key (user_id) references users;
create index IDX_ROBINSON_USER_ID on robinsons (robinson_user_id);
alter table robinsons add constraint FK_ROBINSON_USER_ID foreign key (robinson_user_id) references users;
alter table role_permissions add constraint FKEAD9D23B5682C574 foreign key (role_id) references roles;
alter table role_permissions add constraint FKEAD9D23B70D24902 foreign key (permission_id) references permissions;
create index IDX_VIOLATOR_USER_ID on rule_violations (violator_user_id);
create index IDX_DETECTOR_USER_ID on rule_violations (detector_user_id);
alter table rule_violations add constraint FK_DETECTOR_USER_ID foreign key (detector_user_id) references users;
alter table rule_violations add constraint FK_VIOLATOR_USER_ID foreign key (violator_user_id) references users;
alter table user_addresses add constraint FK9188602EA09C615E foreign key (user_data_id) references user_data;
alter table user_addresses add constraint FK9188602ED634EE8A foreign key (addresses_id) references addresses;
alter table user_contactmethods add constraint FKA59F56868708F8A7 foreign key (contactmethods_id) references contactmethods;
alter table user_contactmethods add constraint FKA59F5686A09C615E foreign key (user_data_id) references user_data;
alter table user_contacts add constraint FKE130BA47A09C615E foreign key (user_data_id) references user_data;
alter table user_contacts add constraint FKE130BA475C8835C9 foreign key (user_contact_id) references users;
create index IDX_PRIMARY_ADDRESS_ID on user_data (primary_address_id);
alter table user_data add constraint FK_USER_DATA_PRIMARY_ADDRESS_ID foreign key (primary_address_id) references addresses;
alter table user_relation_permissions add constraint FKDBE83EB570D24902 foreign key (permission_id) references permissions;
alter table user_relation_permissions add constraint FKDBE83EB51DB40057 foreign key (user_relation_permission_id) references relation_permissions;
alter table user_resources add constraint FKE734A2B1A09C615E foreign key (user_data_id) references user_data;
alter table user_resources add constraint FKE734A2B187A1FB16 foreign key (resources_id) references resources;
alter table user_roles add constraint FK734299495682C574 foreign key (role_id) references roles;
alter table user_roles add constraint FK73429949FBD81D4A foreign key (user_id) references users;
alter table users add constraint FK6A68E083DCC3A0 foreign key (user_data) references user_data;
create index IDX_ZIP_CODES_COUNTRY_ID on zipcodes (country_id);
alter table zipcodes add constraint FK_ZIP_CODES_COUNTRY_ID foreign key (country_id) references countries;
create sequence hibernate_sequence;
