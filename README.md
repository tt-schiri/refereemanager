# Referee Manager

The referee manager is a free, open tool for managing table tennis referees and their assignments.

Part of the project "Open-TT" which provides open documents and applications for table tennis.

License: Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License, see file LICENSE.

At the moment, there is no stable, productive version available.
Several features are working, see changelog.

**Important:** the focus of development at the moment is *features*, not safety.
Therefore, the referee manager is *not* safe for using in the "real" internet.
Use it in a closed envorinment (local server etc.) only.

## Git-Repository

Short information about the structure of the git repository:

The branches are constructed regarding the git branching model of http://nvie.com/posts/a-successful-git-branching-model/

This means, there are always at least three branches:

1. `master` - contains released versions
2. `develop` - main synchronisation branch for feature, release, and hotfix branches
3. `feature/work` - main working branch for development

Additionally, the following branches may occur:

- `feature/*` - writing a special feature
- `release/*` - synchronizing release versions between `develop` and `master`
- `hotfix/*` - fast bugfixes

## Legal stuff

### Licenses

License of the documents: Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License
See file LICENSE.

License of the programs: GNU General Public License, GNU LESSER GENERAL PUBLIC LICENSE.
See file COPYING, COPYING.LESSER.

Which means:

- the documents are free, as long as you
	- don't make money with them
	- mention the creator
	- share derivates with the same license
- programs are free and open source
	- you have to provide the source code of the programs
	- if you change the source code, you have to distribute it under the same license


### Copyright

Copyright 2016 Ekkart Kleinod <ekleinod@edgesoft.de>

The program is distributed under the terms of the GNU General Public License.

See COPYING for details.

This file is part of Open-TT: Referee Manager.

Open-TT: Referee Manager is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Open-TT: Referee Manager is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Open-TT: Referee Manager.  If not, see <http://www.gnu.org/licenses/>.

