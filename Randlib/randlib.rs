extern crate core;

use std::sync::atomic::{AtomicU64, Ordering};

static mut RAND_MAX: u64 = 32767;

static SEED: AtomicU64 = AtomicU64::new(0);

pub fn set_rand_max(value: u64) {
    unsafe {
        RAND_MAX = value;
    }
}
fn new_rand() -> i32 {
    let ticks = std::time::SystemTime::now()
        .duration_since(std::time::SystemTime::UNIX_EPOCH)
        .unwrap()
        .as_nanos() as u64;
    SEED.store(ticks, Ordering::Relaxed);
    unsafe { let value = (8253739_u64.wrapping_mul(SEED.load(Ordering::Relaxed)) + 2396403) % RAND_MAX;
        value as i32 }
}

pub fn rand_int(min: i32, max: i32) -> i32 {
    let (min, max) = if max < min { (max, min) } else { (min, max) };
    let r = new_rand() as u64;
    let mut value = r % ((max - min + 1) as u64) + (min as u64);
    unsafe {
        value %= RAND_MAX;
    }
    value as i32
}

pub fn rand_short(min: i16, max: i16) -> i16 {
    let (min, max) = if max < min { (max, min) } else { (min, max) };
    let r = new_rand() as u64;
    let mut value = r % ((max - min + 1) as u64);
    unsafe {
        value %= RAND_MAX;
    }
    value as i16
}

pub fn rand_long(min: i64, max: i64) -> i64 {
    let (min, max) = if max < min { (max, min) } else { (min, max) };
    let r = new_rand() as u64;
    let mut value = r % ((max - min + 1) as u64) + (min as u64);
    unsafe {
        value %= RAND_MAX;
    }
    value as i64
}

pub fn rand_float(min: f32, max: f32) -> f32 {
    let (min, max) = if max < min { (max, min) } else { (min, max) };
    let r = new_rand() as f32;
    let pre = rand_int(min as i32, max as i32 - 1) as f32;
    unsafe {let mut post = (r / (RAND_MAX as f32)).log10() as f32;
        return pre + post }
}

pub fn rand_double(min: f64, max: f64) -> f64 {
    let (min, max) = if max < min { (max, min) } else { (min, max) };
    let r = new_rand() as f64;
    let pre = rand_int(min as i32, max as i32 - 1) as f64;
    unsafe {let post = (r / (RAND_MAX as f64)).log10();
        return pre + post}
}

pub fn rand_bool() {
    let value = new_rand() % if 5 == 1 { 0 } else { 1 };
}