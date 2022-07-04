use parkinglot;

CREATE TABLE company (
	comp_id int not null auto_increment,
    comp_name varchar(60) not null, 
    comp_cnpj varchar(30) unique not null,
    comp_phone varchar(50) not null,
    comp_update datetime on update now(),
    comp_create datetime not null default now(),
    comp_status int not null default 1,
    
    primary key(comp_id)
);

Create table clientAccount (
	ca_id int not null auto_increment,
    ca_name varchar(100) not null,
    ca_cpf varchar(20) unique not null,
    ca_login varchar(50) unique not null,
    ca_password varchar(50) not null,
    ca_comp_fk int not null,
    ca_update datetime on update now(),
    ca_create datetime not null default now(),
    ca_status int not null default 1,
    
    primary key(ca_id),
    foreign key(ca_comp_fk) references company(comp_id)
);

create table employee(
	emp_id int not null auto_increment,
    emp_name varchar (100) not null,
    emp_login varchar(50) not null unique,
    emp_password varchar(50) not null,
    emp_comp_fk int not null,
    emp_update datetime on update now(),
    emp_create datetime not null default now(),
    emp_status int not null default 1,
    
    primary key(emp_id),
    foreign key(emp_comp_fk) references company(comp_id)
);

describe employee;

-- Vehicle ---------------
create table vehicleColor(
	vc_id int auto_increment not null,
    vc_name varchar(10)  unique not null,
    vc_code varchar(10) unique not null,
	vc_update datetime on update now(),
    vc_create datetime not null default now(),
    vc_status int not null default 1,
    
    primary key(vc_id)
);

create table vehicleType(
	vt_id int not null auto_increment,
    vt_name varchar(50) not null unique, 
    vt_update datetime on update now(),
    vt_create datetime not null default now(),
    vt_status int not null default 1,
    
    primary key(vt_id)
);


create table vehicleBrand(
	vb_id int not null auto_increment,
    vb_name varchar(50) not null unique, 
    vb_update datetime on update now(),
    vb_create datetime not null default now(),
    vb_status int not null default 1,
    
    primary key(vb_id)
);



create table vehicleModel(
	vm_id int not null auto_increment,
    vm_name varchar(50) not null,
    vm_brand_fk int not null,
    vm_year date not null,
    vm_type_fk int not null,
    vm_update datetime on update now(),
    vm_create datetime not null default now(),
    vm_status int not null default 1,
    
    primary key(vm_id),
    foreign key(vm_brand_fk) references vehicleBrand(vb_id),
    foreign key(vm_type_fk) references vehicleType(vt_id)
);

create table vehicleStyle(
	vs_id int auto_increment not null,
    vs_name varchar(50) unique not null,
    vs_type_fk int not null,
    vs_update datetime on update now(),
    vs_create datetime not null default now(),
    vs_status int not null default 1,
    
    primary key(vs_id),
    foreign key(vs_type_fk) references vehicleType(vt_id)
);

create table vehicle (
	ve_id int not null auto_increment,
    ve_plate varchar(15) not null,
    ve_model_fk int not null,
    ve_color_fk int not null,
    ve_style_fk int not null,
    ve_clientAcc_fk int not null,
    
    ve_update datetime on update now(),
    ve_create datetime not null default now(),
    ve_status int not null default 1,
    
    primary key(ve_id),
    foreign key(ve_model_fk) references vehicleModel(vm_id),
    foreign key(ve_color_fk) references vehicleColor(vc_id),
    foreign key(ve_style_fk) references vehicleStyle(vs_id),
    foreign key(ve_clientAcc_fk) references clientAccount(ca_id)
    
);

-- vehicle style
-- vehicle

 ---------------- Parking Lot ------------------------
 
 create table ParkingLot(
	pl_id int auto_increment not null,
    pl_comp_fk int not null,
    
	ve_update datetime on update now(),
    ve_create datetime not null default now(),
    ve_status int not null default 1,
    
    primary key(pl_id),
    foreign key(pl_comp_fk) references company(comp_id)
 );
 
 create table parkingLotAvailability(
	pla_id int not null auto_increment,
    pla_parkingLot_fk int not null,
    pla_type_fk int not null,
    pla_availability int not null,
    
	ve_update datetime on update now(),
    ve_create datetime not null default now(),
    ve_status int not null default 1,
    
    primary key(pla_id),
    foreign key( pla_parkingLot_fk) references ParkingLot(pl_id),
    foreign key( pla_type_fk) references VehicleType(vt_id)
 );
 
  create table parkingSpot(
	ps_id int not null auto_increment,
    ps_code varchar(30) unique not null,
    ps_type_fk int not null,
    ps_parkinglot_fk int not null,
    ps_parkingStatus_fk int not null,
    
	ps_update datetime on update now(),
    ps_create datetime not null default now(),
    ps_status int not null default 1,
    
    primary key(ps_id),
    foreign key( ps_parkinglot_fk) references ParkingLot(pl_id),
    foreign key(ps_parkingStatus_fk) references ParkingSpotStatus(pss_id),
    foreign key( ps_type_fk) references VehicleType(vt_id)
 );
 
 
  create table parkingSpotStatus(
	pss_id int not null auto_increment,
    pss_name varchar(50) unique not null,
    
	pss_update datetime on update now(),
    pss_create datetime not null default now(),
    pss_status int not null default 1,
    
    primary key(pss_id)
 );
 
 create table ParkingSpotStatusToParkingSpot(
	pssps_id int not null auto_increment,
    
    pssps_parkingSpot_fk int not null,
    pssps_parkingStatus_fk int not null,
    
    pssps_update datetime on update now(),
    pssps_create datetime not null default now(),
    pssps_status int not null default 1,
    
    primary key(pssps_id),
    foreign key(pssps_parkingSpot_fk) references ParkingSpot(ps_id),
    foreign key(pssps_parkingStatus_fk) references ParkingSpotStatus(pss_id)
 );
 
 ------------------------------- Parking Card ---------------------
 
 create table ParkingCardType(
	pct_id int not null auto_increment,
    pct_name varchar(30) unique not null,
    pct_hierarchy int default 1,
    pct_price_hour decimal not null,
    pct_type_fk int not null,
    
    pct_update datetime on update now(),
    pct_create datetime not null default now(),
    pct_status int not null default 1,
    
    primary key(pct_id),
    foreign key(pct_type_fk) references vehicleType(vt_id)
 );
 
 create table ParkingCard(
	pc_id int not null auto_increment,
    pc_code varchar(30) unique not null,
    pc_parkingType_fk int not null,
    pc_parkingLot_fk int not null,
    
    pc_update datetime on update now(),
    pc_create datetime not null default now(),
    pc_status int not null default 1,
    
    primary key(pc_id),
    foreign key(pc_parkingType_fk) references ParkingCardType(pct_id),
	foreign key(pc_parkingLot_fk) references ParkingLot(pl_id)
 );
 
 create table ParkingCardClientStatus(
	pccs_id int not null auto_increment,
    pccs_name varchar(30) unique not null,
    
    pccs_update datetime on update now(),
    pccs_create datetime not null default now(),
    pccs_status int not null default 1,
    
    primary key(pccs_id)
 );
 
 
 create table ParkingCardClient(
	pcc_id int not null auto_increment,
    
    pcc_takeoff datetime not null default now(),
    pcc_return datetime,
    pcc_checkout datetime,
    
    pcc_client_fk int not null,
	pcc_pcard_fk int not null,
	pcc_vehicle_fk int not null,
	pcc_cardStatus_fk int not null,
    pcc_employee_fk int not null,
    
    pcc_total_price decimal not null default 0,
    pcc_total_paid decimal not null default 0,
    
    pcc_update datetime on update now(),
    pcc_create datetime not null default now(),
    pcc_status int not null default 1,
    
    primary key(pcc_id),
    foreign key(pcc_client_fk) references clientAccount(ca_id),
	foreign key(pcc_pcard_fk) references ParkingCard(pc_id),
    foreign key(pcc_vehicle_fk) references Vehicle(ve_id),
    foreign key(pcc_cardStatus_fk) references ParkingCardClientStatus(pccs_id),
    foreign key(pcc_employee_fk) references Employee(emp_id)
 );
 
 
 
 
 ------------- Payment -------------------------------
 create table PaymentMethod(
	pm_id int not null auto_increment,
    pm_name varchar(30) unique not null,
    pm_charge decimal not null default 0,
    
    pm_update datetime on update now(),
    pm_create datetime not null default now(),
    pm_status int not null default 1,
    
    primary key(pm_id)
 );
 
 create table PaymentStatus(
	ps_id int not null auto_increment,
    ps_name varchar(30) unique not null,
    pm_message tinytext,
    
    ps_update datetime on update now(),
    ps_create datetime not null default now(),
    ps_status int not null default 1,
    
    primary key(ps_id)
 );
 
 create table Payment (
	pay_id int not null auto_increment,
    
	pay_method_fk int not null,
	pay_paymentStatus_fk int not null,
	pay_employee_fk int not null,
	pay_clientAcc_fk int not null,
    
	pay_initial_price decimal not null,
	pay_discount decimal not null default 0,
	pay_final_price decimal not null  default 0,
	pay_paid_amount decimal not null,
    
	pay_update datetime on update now(),
    pay_create datetime not null default now(),
    pay_status int not null default 1,
    
    primary key(pay_id),
    foreign key(pay_paymentStatus_fk) references PaymentStatus(ps_id),
	foreign key(pay_method_fk) references PaymentMethod(pm_id),
    foreign key(pay_employee_fk) references employee(emp_id),
    foreign key(pay_clientAcc_fk) references ClientAccount(ca_id)
 );
 
 
 create table ParkingPayment(
	pp_id int not null auto_increment,
    pp_payment_fk int not null unique,
    pp_pcardClient_fk int not null,
    
    pp_update datetime on update now(),
    pp_create datetime not null default now(),
    pp_status int not null default 1,
    
    primary key(pp_id),
    foreign key(pp_payment_fk) references Payment(pay_id),
	foreign key(pp_pcardClient_fk) references ParkingCardClient(pcc_id)
 );
 
 
 
 
 

show tables;