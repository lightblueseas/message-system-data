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