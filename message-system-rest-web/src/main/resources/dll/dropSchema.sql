alter table addresses drop constraint FK_ADDRESSES_FEDERALSTATE_ID;
alter table addresses drop constraint FK_ADDRESSES_ZIPCODE_ID;
alter table base_attributes drop constraint FK82063B85D4CCA38D;
alter table base_attributes drop constraint FK82063B8520E98507;
alter table blacklisted_contacts drop constraint FKA1253AB66B955CF9;
alter table blacklisted_contacts drop constraint FKA1253AB6A09C615E;
alter table federalstates drop constraint FK_FEDERAL_STATES_COUNTRY_ID;
alter table message_attachments drop constraint FK64E05778442255DC;
alter table message_attachments drop constraint FK64E05778E812C08D;
alter table message_recipients drop constraint FK_MESSAGE_RECIPIENTS_RECIPIENT_EMAIL;
alter table message_recipients drop constraint FK_MESSAGE_RECIPIENTS_RECIPIENT_ID;
alter table message_recipients drop constraint FK_MESSAGE_RECIPIENTS_MESSAGE_ID;
alter table messages drop constraint FK_MESSAGES_SENDER;
alter table messages drop constraint FK_MESSAGES_SENDER_EMAIL;
alter table messages drop constraint FK_PARENT_MESSAGE_ID;
alter table recommendations drop constraint FK_RECOMMENDATIONS_USER_ID;
alter table recommendations drop constraint FK_RECOMMENDATIONS_RECOMMENDED_ID;
alter table relation_permissions drop constraint FK_USER_RELATION_PERMISSIONS_PROVIDER_ID;
alter table relation_permissions drop constraint FK_USER_RELATION_PERMISSIONS_SUBSCRIBER_ID;
alter table reset_passwords drop constraint FK_RESET_PASSWORDS_USER_ID;
alter table robinsons drop constraint FK_ROBINSON_USER_ID;
alter table role_permissions drop constraint FKEAD9D23B5682C574;
alter table role_permissions drop constraint FKEAD9D23B70D24902;
alter table rule_violations drop constraint FK_DETECTOR_USER_ID;
alter table rule_violations drop constraint FK_VIOLATOR_USER_ID;
alter table user_addresses drop constraint FK9188602EA09C615E;
alter table user_addresses drop constraint FK9188602ED634EE8A;
alter table user_contactmethods drop constraint FKA59F56868708F8A7;
alter table user_contactmethods drop constraint FKA59F5686A09C615E;
alter table user_contacts drop constraint FKE130BA47A09C615E;
alter table user_contacts drop constraint FKE130BA475C8835C9;
alter table user_data drop constraint FK_USER_DATA_PRIMARY_ADDRESS_ID;
alter table user_relation_permissions drop constraint FKDBE83EB570D24902;
alter table user_relation_permissions drop constraint FKDBE83EB51DB40057;
alter table user_resources drop constraint FKE734A2B1A09C615E;
alter table user_resources drop constraint FKE734A2B187A1FB16;
alter table user_roles drop constraint FK734299495682C574;
alter table user_roles drop constraint FK73429949FBD81D4A;
alter table users drop constraint FK6A68E083DCC3A0;
alter table zipcodes drop constraint FK_ZIP_CODES_COUNTRY_ID;
drop table BaseAttributesEntity;
drop table addresses;
drop table attributes;
drop table base_attributes;
drop table blacklisted_contacts;
drop table contactmethods;
drop table countries;
drop table federalstates;
drop table message_attachments;
drop table message_recipients;
drop table messages;
drop table permissions;
drop table recommendations;
drop table relation_permissions;
drop table reset_passwords;
drop table resources;
drop table robinsons;
drop table role_permissions;
drop table roles;
drop table rule_violations;
drop table user_addresses;
drop table user_contactmethods;
drop table user_contacts;
drop table user_data;
drop table user_relation_permissions;
drop table user_resources;
drop table user_roles;
drop table users;
drop table zipcodes;
drop sequence hibernate_sequence;
