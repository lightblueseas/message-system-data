alter table messages drop column state;    
alter table messages add state messageStateType;

alter table messages drop column messagetype;    
alter table messages add messagetype messageTypeType;

alter table contactmethods drop column contactmethod;    
alter table contactmethods add contactmethod contactmethodType;

alter table user_data drop column gender;    
alter table user_data add gender genderType;