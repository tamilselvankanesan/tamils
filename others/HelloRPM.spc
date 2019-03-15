Name: mp		
Version: 1.0
Release: 1
Summary: Hello world	

#### create directory structure using rpmdev-setuptree command or directly create directories - /home/tamil/rpmbuild BUILD  BUILDROOT  RPMS  SOURCES  SPECS  SRPMS  tmp
## buildroot will be /home/tamil/rpmbuild/BUILDROOT/mp-1.0-1.x86_64
### copy the necessary files to the buildroot during install phase... and list the files under files section.. then build using rpmbuild -bb command
### the directory name under build root will be packagename-version-release.target

#Group:		
License: GPL	
URL:	http://www.tamils.rocks	
#Source0:	

#BuildRequires:	
#Requires:	

%description
SImple hello world RPM

#%prep
#%setup -q


#%build
#%configure
#make %{?_smp_mflags}


%install
cp -r /home/tamil/buildroot/ %{buildroot}
cd %{buildroot}
mv buildroot br

%files
/br/dir1/sdir1/a.xtxt
/br/dir1/b.txt
/br/dir2/c.txt


%changelog
* Tue Mar 05 2019 Tamil- 1.0-1
- Test package
