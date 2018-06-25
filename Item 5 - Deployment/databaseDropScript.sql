start transaction;

use `AcmeNewspaper`;

revoke all privileges on `AcmeNewspaper`.* from 'acme-user'@'%';
revoke all privileges on `AcmeNewspaper`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `AcmeNewspaper`;

commit;